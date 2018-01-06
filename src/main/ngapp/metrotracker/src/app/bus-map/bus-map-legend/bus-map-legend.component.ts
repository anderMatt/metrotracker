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
    }`
  ]
})
export class BusMapLegendComponent implements OnInit {
  tripHeadsigns: string[];

  @Input() set busPositions(positions: BusPosition[]) {
    if(!positions) {
      return;
    }
    this.tripHeadsigns = this.extractTripHeadsigns(positions);
  }

  constructor(private busColorService: BusColorService) { }

  ngOnInit() {
  }

  getHeadsignLabelColor(tripHeadsign: string) {
    return this.busColorService.getHeadsignLegendColor(tripHeadsign);
  }

  private extractTripHeadsigns(busPositions: BusPosition[]): string[] {
    const headSigns = new Set();
    busPositions.forEach((p: BusPosition) => {
      headSigns.add(p.tripHeadsign);
    });
    return Array.from(headSigns).sort();
  }

}
