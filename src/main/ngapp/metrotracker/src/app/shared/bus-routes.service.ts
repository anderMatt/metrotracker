import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BusRoute} from "./BusRoute";
import {Observable} from "rxjs/Observable";


@Injectable()
export class BusRoutesService {

  constructor(private http: HttpClient) { }

  getBusRoutes(): Observable<BusRoute[]> {
    const apiUrl = "/api/bus/routes";
    return this.http.get<BusRoute[]>(apiUrl)
      //TODO: err handling
    //TODO: save to local storage?
  }
}
