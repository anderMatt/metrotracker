import {Component, OnInit} from '@angular/core';
import {BusPositionsService} from "./shared/bus-positions.service";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/switchMap";
import "rxjs/add/observable/interval";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private busPositionsService: BusPositionsService) {}

  ngOnInit() {
    this.busPositionsService.busPositions$
      .switchMap(positions => Observable.interval(60000))  //Refresh every 60 seconds.
      .subscribe(() => {
          this.busPositionsService.refreshBusPositions()
      });
  }

  onRouteSelected(routeId: string) {
    this.busPositionsService.loadBusPositions(routeId);
  }
}
