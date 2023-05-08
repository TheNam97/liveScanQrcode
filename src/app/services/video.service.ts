import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject, Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class VideoService {
  constructor(
    private http: HttpClient
  ) { }

  private playingState = new Subject<boolean>();
  private loading = new BehaviorSubject<boolean>(true);
  private videoEnded = new BehaviorSubject<boolean>(false);

  private dataSource = new BehaviorSubject(null);
  getData = this.dataSource.asObservable();
  sendData(param: any) {
    this.dataSource.next(param);
  }

  // headers: { 'Content-Type': 'application/json'}

  // public setHeaders(): any{ //{ 'Content-Type': 'application/json' }
  //   const headers= new HttpHeaders().set('Content-Type','application/json')
  //   return headers
  // }

  public getLatest(): Observable<any> {
    // const headers = this.setHeaders()
    return this.http.get<any>('http://localhost:8082/api/qrcode/getLatest',{
      // headers: {
      //   "Access-Control-Allow-Origin": "*",
      //   "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
      //   'Access-Control-Allow-Headers': 'Content-Type, X-Auth-Token, Origin, Authorization'
      //  },
      observe: 'response',
      responseType:'json'
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
