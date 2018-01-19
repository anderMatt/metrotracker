import {Component, Input, OnInit} from '@angular/core';
import {BusPosition} from "../../shared/BusPosition";
import {BusColorService} from "../../shared/bus-color.service";

@Component({
  selector: 'app-bus-map-legend',
  templateUrl: './bus-map-legend.component.html',
  // styleUrls: ['./bus-map-legend.component.css']
  styles: [
    `ul {
      padding-left: 0;
    }
    li {
      color: white;
      font-weight: bold;
    }
    .bus-count {
      font-size: 1.3em;
    }`
  ]
})
export class BusMapLegendComponent implements OnInit {
  tripHeadsigns: string[];
  numBuses = 0;
  @Input() lastUpdated: Date;
  @Input() set busPositions(positions: BusPosition[]) {
    if(!positions) {
      return;
    }
    this.tripHeadsigns = this.extractTripHeadsigns(positions);
    this.numBuses = positions.length;
    this.lastUpdated = new Date();
  }

  constructor(private busColorService: BusColorService) { }

  ngOnInit() {
  }

  getHeadsignLabelColor(tripHeadsign: string) {
    return this.busColorService.getHeadsignLegendColor(tripHeadsign);
  }

  getBusCountString() {
    var str;
    if(this.numBuses > 1 ) {
      str = `${this.numBuses} buses are currently running.`;
    }
    else if(this.numBuses == 1) {
      str = '1 bus is currently running.'
    }
    else {
      str = 'No buses are currently running.'
    }
    return str;
  }

  private extractTripHeadsigns(busPositions: BusPosition[]): string[] {
    const headSigns = new Set();
    busPositions.forEach((p: BusPosition) => {
      headSigns.add(p.tripHeadsign);
    });
    return Array.from(headSigns).sort();
  }
}
