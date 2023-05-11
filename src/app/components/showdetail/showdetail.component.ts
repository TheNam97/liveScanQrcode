import { Component, OnInit } from '@angular/core';
import {IQrCode} from "../../model";
import {VideoService} from "../../services/video.service";

@Component({
  selector: 'app-showdetail',
  templateUrl: './showdetail.component.html',
  styleUrls: ['./showdetail.component.scss']
})
export class ShowdetailComponent implements OnInit {
  public qrcode: IQrCode;
  public typeShow: any;
  constructor( private videoService: VideoService, ) { }

  public ngOnInit(): void {
    const show = this.videoService.getDataDetail.subscribe( (dataDetail: any) => {
      this.qrcode = dataDetail.content;
      this.typeShow = dataDetail.typeShow;
    })
    show.unsubscribe();
  }

}
