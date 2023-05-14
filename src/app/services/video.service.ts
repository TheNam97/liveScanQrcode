import {Injectable} from '@angular/core';
import {BehaviorSubject, Subject, Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class VideoService {
  constructor(
    private http: HttpClient
  ) {
  }

  private playingState = new Subject<boolean>();
  private loading = new BehaviorSubject<boolean>(true);
  private videoEnded = new BehaviorSubject<boolean>(false);

  private dataSourceQrcode = new BehaviorSubject(null);
  getQrcode = this.dataSourceQrcode.asObservable();

  sendQrcode(param: any) {
    this.dataSourceQrcode.next(param);
  }

  private dataSource = new BehaviorSubject(null);
  getData = this.dataSource.asObservable();

  sendData(param: any) {
    this.dataSource.next(param);
  }

  private dataSourceDetail = new BehaviorSubject(null);
  getDataDetail = this.dataSourceDetail.asObservable();

  sendDataDetail(param: any) {
    this.dataSourceDetail.next(param);
  }

  // headers: { 'Content-Type': 'application/json'}

  // public setHeaders(): any{ //{ 'Content-Type': 'application/json' }
  //   const headers= new HttpHeaders().set('Content-Type','application/json')
  //   return headers
  // }

  public getRoboFlowQrcode(imgBase64: any) {
    return this.http.post<any>('https://detect.roboflow.com/qr-bq04z/1',imgBase64, {
      params: {
            api_key: 'bqGjAG1m40DKlg4whQYw'
      },
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      observe: 'response',
      responseType: 'json'
    });
  }

  public getRoboFlowGoods(imgBase64: any) {
    return this.http.post<any>('https://detect.roboflow.com/nhan-dien-kien-hang1/1',imgBase64, {
      params: {
        api_key: 'bqGjAG1m40DKlg4whQYw'
      },
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      observe: 'response',
      responseType: 'json'
    });
  }

  public getLatest(itemCode: any): Observable<any> {
    // const headers = this.setHeaders()
    return this.http.get<any>(`http://localhost:8082/api/qrcode/getLatest/${itemCode}`, {
      observe: 'response',
      responseType: 'json'
    });
  }

  public getListQrcode(req: any): Observable<any> {
    // const headers = this.setHeaders()
    return this.http.get<any>('http://localhost:8082/api/qrcode/getListQrcode', {
      params: req,
      observe: 'response',
      responseType: 'json'
    });
  }

  public get loading$(): Observable<boolean> {
    return this.loading.asObservable();
  }

  public setLoading(value: boolean): void {
    this.loading.next(value);
  }

  public play(): void {
    this.playingState.next(true);
  }

  public pause(): void {
    this.playingState.next(false);
  }

  public get playingState$(): Observable<boolean> {
    return this.playingState.asObservable();
  }

  public get videoEnded$(): Observable<boolean> {
    return this.videoEnded.asObservable();
  }

  public setVideoEnded(value: boolean): void {
    this.videoEnded.next(value);
  }
}
