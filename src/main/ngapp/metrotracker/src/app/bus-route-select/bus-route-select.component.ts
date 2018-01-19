import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {FormControl} from "@angular/forms";

import {BusRoute} from "../shared/BusRoute";
import "rxjs/add/operator/debounceTime";
import "rxjs/add/operator/distinctUntilChanged";
import {BusRoutesService} from "../shared/bus-routes.service";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-bus-route-select',
  templateUrl: './bus-route-select.component.html',
  styleUrls: ['./bus-route-select.component.css']
})
export class BusRouteSelectComponent implements OnInit, OnDestroy {
  busRoutes: BusRoute[] = [];
  routeControl = new FormControl();
  selectSub: Subscription;
  @Output() routeSelected: EventEmitter<string> = new EventEmitter();

  constructor(private busRoutesService: BusRoutesService) { }

  ngOnInit() {
    this.busRoutesService.getBusRoutes()
      .subscribe((routes: BusRoute[]) => {
        this.busRoutes = routes;
        this.initSelectSubscription();
      });
    // TODO: err handler.
  }

  ngOnDestroy() {
    this.selectSub.unsubscribe();
  }

  private initSelectSubscription() {
    this.selectSub = this.routeControl.valueChanges
      .debounceTime(200)
      .distinctUntilChanged()
      .subscribe(selectedRoute => {
        let routeId = selectedRoute.split(' ')[0];
        this.routeSelected.emit(routeId);
      });
  }
}
