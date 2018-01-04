import { Component } from '@angular/core';
import {BusPositionsService} from "./shared/bus-positions.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private busPositionsService: BusPositionsService) {}

  testApiCall() {
    console.log('testing api call...');
    this.busPositionsService.loadBusPositions("D2");
  }
}
