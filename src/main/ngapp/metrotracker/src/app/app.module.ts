import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {AgmCoreModule} from "@agm/core";

import { AppComponent } from './app.component';
import { BusPositionsService } from "./shared/bus-positions.service";
import { BusColorService } from "./shared/bus-color.service";
import { BusRouteSelectComponent } from './bus-route-select/bus-route-select.component';
import { BusMapComponent } from './bus-map/bus-map.component';
import { BusMapLegendComponent } from './bus-map/bus-map-legend/bus-map-legend.component';

import {MAP_CONFIG, MapInitConfig} from './app.config';


@NgModule({
  declarations: [
    AppComponent,
    BusRouteSelectComponent,
    BusMapComponent,
    BusMapLegendComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAG5VRJzKYMI2ejciz_BNumXls-K42WuBo'
    })
  ],
  providers: [
    BusPositionsService,
    BusColorService,
    {
      provide: MAP_CONFIG,
      useValue: MapInitConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
