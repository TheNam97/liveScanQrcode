import {Component, OnInit} from '@angular/core';
import {VideoService} from 'src/app/services/video.service';
import PlaylistItem from 'src/app/interfaces/playlist-item.interface';
import {VideoPlaylistService} from 'src/app/services/video-playlist.service';
import {IQrCode, QrCode} from 'src/app/model';

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
  public listResult: IQrCode[] = null;
  public img: any;

  public page: any = 1;
  public itemsPerPage = 10;
  public maxPage: any
  public searchOption: any;
  public total: any = 0;

  public qrcode: IQrCode;

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
////////////////////////////////////////////////////////
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
  this.getListQrcode();
    setInterval(
      () => {
        this.videoService.getLatest().subscribe(getData => {
          if (getData !== null && getData !== undefined)
            this.qrcode = getData.body.data
          this.videoService.sendQrcode(this.qrcode);
          // console.log('this.qrcode',this.qrcode)
        })
      }
      , 1000
    );

    // const getData = this.videoService.getData.subscribe( (data:any) => {
    //   if( data != null ) {
    //     this.img = data.img;
    //     getData.unsubscribe();
    //   }
    // });
  }
  public getListQrcode(){
    this.searchOption = {}
    this.searchOption = {
      page: this.page - 1,
      size: this.itemsPerPage,
    }
    this.videoService.getListQrcode(
      {
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: 'id: DESC',
        searchOption: JSON.stringify(this.searchOption),
      }
    ).subscribe( dataListQrcode => {
      this.listResult = dataListQrcode.body.content;
      this.total = dataListQrcode.body.totalElements;
      for(let i =0 ; i< this.listResult.length; i++){
        this.listResult[i].imgGoods = 'data:image/jpeg;base64,' + this.listResult[i].imgGoods;
        this.listResult[i].imgQrcode = 'data:image/jpeg;base64,' + this.listResult[i].imgQrcode;
      }
      // console.log(dataListQrcode.body.totalElements,'this.listResult',this.listResult)
      this.calculator(dataListQrcode.body.totalElements)
    })
  }

  public loadPage(pageChangeEvent: any) {
  }

  public calculator(sizeTotal :any) {
    this.maxPage = Math.ceil(sizeTotal / this.itemsPerPage);
  }

  public sau() {
    this.calculator(this.total);
    if (this.page < this.maxPage) {
      this.page++;
      this.getListQrcode()
    }
  }

  public truoc() {
    this.calculator(this.total);
    if (this.page > 1) {
      this.page--;
      this.getListQrcode()
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
