import {Component, Input, OnInit} from '@angular/core';
import {BusPosition} from "../../shared/BusPosition";

@Component({
  selector: 'app-bus-map-infowindow',
  templateUrl: './bus-map-infowindow.component.html',
  // styleUrls: ['./bus-map-infowindow.component.css']
  styles: [
    `.late {
      color: red;
    }
    .on-time {
      color: green;
    }`
  ]
})
export class BusMapInfowindowComponent {
  @Input() busPosition: BusPosition;

  constructor() { }

  getScheduleStatusMessage() {
    if(this.busPosition.Deviation === 0) {
      return "Running on schedule.";
    }
    let minutesLabel = (this.busPosition.Deviation === 1 ? " minute" : " minutes");
    let statusLabel = this.isBehindSchedule() ? " behind schedule" : " ahead of schedule";
    return Math.abs(this.busPosition.Deviation) + minutesLabel + statusLabel;
    //Math.abs so we don't display a negative number if bus is behind schedule.
  }

  isBehindSchedule() {
    return (this.busPosition.Deviation < 0);
  }


}
