import { Injectable } from '@angular/core';
import {BusPositionsService} from "./bus-positions.service";
import {BusPosition} from "./BusPosition";

@Injectable()
export class BusColorService {
  private busIcons: string[] = [];
  private busLegendColors: string[] = ["green", "orange"];
  private routeHeadsigns: string[] = [];

  constructor(private busPositionsService: BusPositionsService) {
    this.busPositionsService.busPositions$
      .subscribe((positions: BusPosition[]) => {
        this.routeHeadsigns = this.extractTripHeadsigns(positions);
      });
  }

  public getHeadsignLegendColor(tripHeadsign: string): string {
    const headsignIndex = this.routeHeadsigns.indexOf(tripHeadsign);
    if(headsignIndex === -1 || headsignIndex >= this.busLegendColors.length) {
      return "";
    }
    return this.busLegendColors[this.routeHeadsigns.indexOf(tripHeadsign)];
  }

  //Returns array of all bus destinations. Sorted in ABC order.
  private extractTripHeadsigns(busPositions: BusPosition[]): string[] {
    const headSigns = new Set();
    busPositions.forEach((p: BusPosition) => {
      headSigns.add(p.tripHeadsign);
    });
    return Array.from(headSigns).sort();
  }

}
