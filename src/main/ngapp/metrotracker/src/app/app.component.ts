import {Component} from '@angular/core';
import {BusPositionsService} from "./shared/bus-positions.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private busPositionsService: BusPositionsService) {}

  onRouteSelected(routeId: string) {
    this.busPositionsService.loadBusPositions(routeId);
  }
}
