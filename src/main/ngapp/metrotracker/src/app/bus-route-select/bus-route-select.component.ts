import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl} from "@angular/forms";

import "rxjs/add/operator/debounceTime";
import "rxjs/add/operator/distinctUntilChanged";

@Component({
  selector: 'app-bus-route-select',
  templateUrl: './bus-route-select.component.html',
  styleUrls: ['./bus-route-select.component.css']
})
export class BusRouteSelectComponent implements OnInit {
  busRoutes = ["D2", "X2"];//, "ldskfjslkfdfkjdlfskjfsfjiopsdfjsdfksdjfosijfsdfjsodf   dlskfjsldfusodifjsf"];
  routeControl = new FormControl();
  @Output() routeSelected: EventEmitter<string> = new EventEmitter();

  constructor() { }

  ngOnInit() {
    this.routeControl.valueChanges
      .debounceTime(200)
      .distinctUntilChanged()
      .subscribe(selectedRoute => {
        let routeId = selectedRoute.split(' ')[0];
        console.log("inside select compnent. routeId to emit: " + routeId);
        this.routeSelected.emit(routeId);
      });
  }

}
