export interface BusPosition { //TODO: change Deviation to number when returning from service?
  dateTime: Date;
  Deviation: number;
  directionNum: string;
  directionText: string;
  lat: number;
  lon: number;
  tripEndTime: string;
  tripHeadsign: string;
  tripStartTime: string;
  vehicleID: string;
}
