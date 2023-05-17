import { Component, OnInit } from '@angular/core';
import {IQrCode} from "../../model";
import {VideoService} from "../../services/video.service";

@Component({
  selector: 'app-result-real-time',
  templateUrl: './result-real-time.component.html',
  styleUrls: ['./result-real-time.component.scss']
})
export class ResultRealTimeComponent implements OnInit {

  public img: any;
  public qrcode: IQrCode

  constructor(
    private videoService: VideoService,
  ) { }

  public ngOnInit(): void {
    this.videoService.getQrcode.subscribe(getQrcode => {
      console.log('getQrcode',getQrcode)
      if(getQrcode !== null && getQrcode !== undefined){
        this.qrcode = getQrcode
        this.qrcode.senderAddress = 'Địa chỉ gửi: '+ this.qrcode.senderAddress
        this.qrcode.receiverAddress = 'Địa chỉ nhận: '+ this.qrcode.receiverAddress
        this.qrcode.imgGoods = 'data:image/jpeg;base64,' + this.qrcode.imgGoods
        this.qrcode.imgQrcode = 'data:image/jpeg;base64,' + this.qrcode.imgQrcode
      }
    })
  }

}
