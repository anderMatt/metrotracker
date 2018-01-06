import { Injectable } from '@angular/core';
import {BusPositionsService} from "./bus-positions.service";
import {BusPosition} from "./BusPosition";

@Injectable()
export class BusColorService {
  private busIcons: string[] = ["assets/bus-01.png", "assets/bus-02.png", "assets/bus-03.png"];
  private busLegendColors: string[] = ["#590A30", "#EF6125", "#90AA3C"];
  private routeHeadsigns: string[] = [];

  constructor(private busPositionsService: BusPositionsService) {
    this.busPositionsService.busPositions$
      .subscribe((positions: BusPosition[]) => {
        this.routeHeadsigns = this.extractTripHeadsigns(positions);
      });
  }

  public getHeadsignLegendColor(tripHeadsign: string): string {
    //TODO: text color also. {bgc:'', tc: ''}
    const headsignIndex = this.routeHeadsigns.indexOf(tripHeadsign);
    if(headsignIndex === -1 || headsignIndex >= this.busLegendColors.length) {
      return "";
    }
    return this.busLegendColors[headsignIndex];
  }

  public getBusIconUrl(tripHeadsign: string): string {
    const headsignIndex = this.routeHeadsigns.indexOf(tripHeadsign);
    if(headsignIndex === -1 || headsignIndex >= this.busIcons.length) {
      return "";
    }
    return this.busIcons[headsignIndex];
  }

  //Returns array of all bus destinations. Sorted in ABC order so that colors
  // are consistent across requests.
  private extractTripHeadsigns(busPositions: BusPosition[]): string[] {
    const headSigns = new Set();
    busPositions.forEach((p: BusPosition) => {
      headSigns.add(p.tripHeadsign);
    });
    return Array.from(headSigns).sort();
  }

}
