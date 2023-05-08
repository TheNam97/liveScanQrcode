import { Component, ElementRef, Input, ViewChild, OnInit } from '@angular/core';
import * as HLS from 'hls.js';
import { VideoService } from 'src/app/services/video.service';
import { VolumeService } from 'src/app/services/volume.service';
import { VideoTimeService } from 'src/app/services/video-time.service';
import { VideoPlaylistService } from 'src/app/services/video-playlist.service';
import jsQR from 'jsqr';

@Component({
  selector: 'app-video-wrapper',
  templateUrl: './video-wrapper.component.html',
  styleUrls: ['./video-wrapper.component.scss']
})
export class VideoWrapperComponent implements OnInit {
  public loading: boolean;
  public ignore: boolean;
  public playing = false;
  public timer: any;
  public checkRun: any = 0;
  public object: any;
  public stop: any = 0;

  private hls = new HLS();
  private videoListeners = {
    loadedmetadata: () => this.videoTimeService.setVideoDuration(this.video.nativeElement.duration),
    canplay: () => this.videoService.setLoading(false),
    seeking: () => this.videoService.setLoading(true),
    timeupdate: () => {
      if (!this.ignore) {
        this.videoTimeService.setVideoProgress(this.video.nativeElement.currentTime);
      }
      if (
        this.video.nativeElement.currentTime === this.video.nativeElement.duration &&
        this.video.nativeElement.duration > 0
      ) {
        this.videoService.pause();
        this.videoService.setVideoEnded(true);
      } else {
        this.videoService.setVideoEnded(false);
      }
    }
  };

  @ViewChild('video', { static: true }) private readonly video: ElementRef<HTMLVideoElement>;

  constructor(
    private videoService: VideoService,
    private volumeService: VolumeService,
    private videoTimeService: VideoTimeService,
    private videoPlaylistService: VideoPlaylistService
  ) {}

  public ngOnInit() {
    this.subscriptions();
    Object.keys(this.videoListeners).forEach(videoListener =>
      this.video.nativeElement.addEventListener(videoListener, this.videoListeners[videoListener])
    );

    this.timer = setInterval(
      () => {
        if (this.checkRun === 0) {
          this.getImgFromVideo1();
        } else {
          clearInterval(this.timer);
        }
      }
      , 500
    );
  }

  public getImgFromVideo1() {
// Create a canvas element
      const video = document.querySelector('video');
      const canvas = document.createElement('canvas');
      canvas.width = video.videoWidth;
      canvas.height = video.videoHeight;
// Get the 2D context of the canvas
      const ctx = canvas.getContext('2d', {willReadFrequently: true}) as CanvasRenderingContext2D;
// Attach a listener to start decoding QR codes when the video is playing
      video.addEventListener('play', () => {
          // Define the function to capture video frames and decode QR codes
          const decodeQR = () => {
              if (canvas.width > 0 && canvas.height > 0) {
                // Capture a frame from the video and draw it on the canvas
                ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
                const imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
                // Decode QR code from the image data
                const code = jsQR(imageData.data, imageData.width, imageData.height);
                // If a QR code is detected, do something with the result
                if (code && this.stop === 0) {
                  this.checkRun++;
                  this.object = {}
                  this.object.img = canvas.toDataURL()
                  this.object.code = code.data
                  this.videoService.sendData(this.object);

                  console.log('this.object', this.object)
                  this.stop = 1;
                }
              }
              // Request the next frame
            if (this.stop === 0) {
              requestAnimationFrame(decodeQR);
            }
            }
          ;
          // Start decoding QR codes
        if (this.stop === 0) {
          requestAnimationFrame(decodeQR);
        }
      });

  }

  /** Play/Pause video on click */
  public onVideoClick() {
      if (this.playing) {
        this.videoService.pause();
      } else {
        this.videoService.play();
      }
  }

  /** Go full screen on double click */
  public onDoubleClick() {
    if (document.fullscreenElement) {
      document.exitFullscreen();
    } else {
      const videoPlayerDiv = document.querySelector('.video-player');
      if (videoPlayerDiv.requestFullscreen) {
        videoPlayerDiv.requestFullscreen();
      }
    }
  }

  /**
   * Loads the video, if the browser supports HLS then the video use it, else play a video with native support
   */
  public load(currentVideo: string): void {
    if (HLS.isSupported()) {
      this.loadVideoWithHLS(currentVideo);
    } else {
      if (this.video.nativeElement.canPlayType('application/vnd.apple.mpegurl')) {
        this.loadVideo(currentVideo);
      }
    }
  }

  /**
   * Play or Pause current video
   */
  private playPauseVideo(playing: boolean) {
    this.playing = playing;
    this.video.nativeElement[playing ? 'play' : 'pause']();
  }

  /**
   * Setup subscriptions
   */
  private subscriptions() {
    this.videoService.playingState$.subscribe(playing => this.playPauseVideo(playing));
    this.videoPlaylistService.currentVideo$.subscribe(video => this.load(video));
    this.videoTimeService.currentTime$.subscribe(currentTime => (this.video.nativeElement.currentTime = currentTime));
    this.volumeService.volumeValue$.subscribe(volume => (this.video.nativeElement.volume = volume));
    this.videoService.loading$.subscribe(loading => (this.loading = loading));
    this.videoTimeService.ignore$.subscribe(ignore => (this.ignore = ignore));
  }

  /**
   * Method that loads the video with HLS support
   */
  private loadVideoWithHLS(currentVideo: string) {
    this.hls.loadSource(currentVideo);
    this.hls.attachMedia(this.video.nativeElement);
    // this.hls.on(HLS.Events.MANIFEST_PARSED, () => this.video.nativeElement.play());
  }

  /**
   * Method that loads the video without HLS support
   */
  private loadVideo(currentVideo: string) {
    this.video.nativeElement.src = currentVideo;
  }
}
