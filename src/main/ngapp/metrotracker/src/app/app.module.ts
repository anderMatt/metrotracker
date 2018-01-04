import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from "@angular/common/http";


import { AppComponent } from './app.component';
import { BusPositionsService } from "./shared/bus-positions.service";
import { BusColorService } from "./shared/bus-color.service";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [
    BusPositionsService,
    BusColorService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
