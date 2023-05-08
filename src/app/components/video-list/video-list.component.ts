import { Component, OnInit } from '@angular/core';
import { VideoService } from 'src/app/services/video.service';
import PlaylistItem from 'src/app/interfaces/playlist-item.interface';
import { VideoPlaylistService } from 'src/app/services/video-playlist.service';

@Component({
  selector: 'app-video-list',
  templateUrl: './video-list.component.html',
  styleUrls: ['./video-list.component.scss']
})
export class VideoListComponent implements OnInit {
  public playNext: boolean;
  public videoEnded: boolean;
  public videoList: ({ name: string, selected: boolean })[] = [];
  public timer: any;
  public listResult: any[] = [{},{},{},{},{},{},{},{},{},{},{}];
  public img: any;

  public page: any = 1;
  public itemsPerPage = 10;
  public maxPage:any

  private list: PlaylistItem[] = [];
  private activeVideo = 0;

  constructor(
    private videoService: VideoService,
    private videoPlaylistService: VideoPlaylistService
  ) {

  }

  public ngOnInit() {
    this.videoPlaylistService.list$.subscribe(list => (this.list = list));
    this.videoPlaylistService.currentVideo$.subscribe(currentVideo => {
      this.videoList = this.list.map(item => ({
        name: item.title,
        selected: item.url === currentVideo
      }));
    });
    this.videoPlaylistService.fetchList('./assets/playlist.json');
    this.videoPlaylistService
      .shouldPlayNext$
      .subscribe(playNext => (this.playNext = playNext));
    this.videoService.videoEnded$.subscribe(ended => {
      if (this.playNext && ended) {
        this.videoPlaylistService.playNextVideo();
        this.videoService.play();
      }
    });

    // let count = 0;
    // this.timer = setInterval(
    //   () => {
    //     if (count < 30 ) {
    //       count++;
    //       this.videoService.play();
    //       console.log('aaaaa')
    //     } else {
    //       clearInterval(this.timer);
    //     }
    //   }
    //   , 100
    // );

    setInterval(
        () => {
          this.videoService.getLatest().subscribe(data => {
            console.log('data',data)
          })
        }
        , 500
      );


    const getData = this.videoService.getData.subscribe( (data:any) => {
      if( data != null ) {
        this.img = data.img;
        getData.unsubscribe();
      }
    });
    this.calculator()
  }
  public loadPage(pageChangeEvent: any) {
  }

  public calculator(){
    this.maxPage = Math.ceil(this.listResult.length / 10);
  }

  public sau(){
    if( this.page < this.maxPage ) {
      this.page++;
    }
  }

  public truoc(){
    if( this.page > 1){
      this.page--;
    }
  }

  public playIt(index: number): void {
    this.videoPlaylistService.setCurrentVideoByIndex(index);
    this.videoService.play();
    this.activeVideo = index;
  }

  public onChange(): void {
    this.playNext = !this.playNext;
    this.videoPlaylistService.setShouldPlayNext(this.playNext);
  }
}
