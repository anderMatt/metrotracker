import { Injectable } from '@angular/core';

import {BusPosition} from "./BusPosition";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/distinctUntilChanged";
import "rxjs/add/operator/share";
import "rxjs/add/operator/map";

@Injectable()
export class BusPositionsService {

  private currentRouteId: string;
  private _busPositionsSource: BehaviorSubject<BusPosition[]> = new BehaviorSubject([]);
  public busPositions$: Observable<BusPosition[]> = this._busPositionsSource.asObservable().distinctUntilChanged().share();

  constructor(private httpClient: HttpClient) { }

  loadBusPositions(routeId: string) {
    //TODO: backend action flag for indicators.
    const apiUrl = "/api/bus/positions?routeId=" + routeId; //TODO: inject token? https://stackoverflow.com/questions/37265275/how-to-implement-class-constants-in-typescript

    this.httpClient.get<BusPosition[]>(apiUrl)
      .map((pos: BusPosition[]) => this.doJsonStringTypeConversion(pos))
      .subscribe( (positions: BusPosition[]) => {
        this.currentRouteId = routeId;
        this._busPositionsSource.next(positions);
      },
        (err: any) => console.log('Got http sub err: ' + err));  //TODO: err handler
  }

  refreshBusPositions() {
    if(!this.currentRouteId) {
      return;
    }
    this.loadBusPositions(this.currentRouteId);
  }

  //Map json response to correct types. TODO: better way?
  private doJsonStringTypeConversion(positionsJson: BusPosition[]): BusPosition[] {
    return positionsJson.map((p: BusPosition) => {
      p.dateTime = new Date(p.dateTime);
      p.lat = parseInt(<string><any> p.lat);
      p.lon = parseInt(<string><any> p.lon);
      return p;
    });
  }

}
