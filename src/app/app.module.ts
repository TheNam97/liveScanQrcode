import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ControlsComponent } from './components/controls/controls.component';
import { VideoWrapperComponent } from './components/video-wrapper/video-wrapper.component';
import { ControlComponent } from './components/control/control.component';
import { ProgressBarComponent } from './components/progress-bar/progress-bar.component';
import { ControlVolumeComponent } from './components/control-volume/control-volume.component';
import { TimeComponent } from './components/time/time.component';
import { VideoListComponent } from './components/video-list/video-list.component';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatListModule } from '@angular/material/list';

import { PopoverModule } from 'ngx-bootstrap/popover';
import {PaginationModule} from 'ngx-bootstrap';
import {FormsModule} from '@angular/forms';
import {ShowComponent} from "./components/show/show.component";

import { HttpClientModule } from '@angular/common/http';
import {MatDialogModule} from "@angular/material/dialog";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ShowdetailComponent } from './components/showdetail/showdetail.component';

@NgModule({
  declarations: [
    AppComponent,
    ControlsComponent,
    VideoWrapperComponent,
    ControlComponent,
    ProgressBarComponent,
    ControlVolumeComponent,
    TimeComponent,
    VideoListComponent,
    ShowComponent,
    ShowdetailComponent
  ],
  imports: [
    PopoverModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    MatSlideToggleModule,
    MatProgressSpinnerModule,
    MatSliderModule,
    MatListModule,
    PaginationModule.forRoot(),
    FormsModule,
    HttpClientModule,
    MatDialogModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
