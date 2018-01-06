import { Component, OnInit, Inject } from '@angular/core';
import {BusPositionsService} from "../shared/bus-positions.service";
import {BusPosition} from "../shared/BusPosition";
import {MAP_CONFIG, MapConfiguration} from "../app.config";
import {BusColorService} from "../shared/bus-color.service";

@Component({
  selector: 'app-bus-map',
  templateUrl: './bus-map.component.html',
  // styleUrls: ['./bus-map.component.css']
  styles: [
      `agm-map {
        height: 600px;
      }
    .map-container {
      border: 1px solid black;
    }`
  ]
})
export class BusMapComponent implements OnInit {
  busPositions: BusPosition[];

 constructor (
   private busPositionsService: BusPositionsService,
   @Inject(MAP_CONFIG) public mapConfig: MapConfiguration,
   private busColorService: BusColorService) { }

  ngOnInit() {
   console.log("Inside busmap ngOnInit");
   this.busPositionsService.busPositions$
     .subscribe((bp: BusPosition[]) => {
     this.busPositions = bp;
     console.log("BusMap bp set to: " + JSON.stringify(this.busPositions));
     });
  }

  getBusIcon(tripHeadsign: string) {
   return this.busColorService.getBusIconUrl(tripHeadsign);
  }
}
