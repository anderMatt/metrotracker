import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from "@angular/common/http";


import { AppComponent } from './app.component';
import { BusPositionsService } from "./shared/bus-positions.service";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [BusPositionsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
