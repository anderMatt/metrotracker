import {InjectionToken} from "@angular/core";

export interface MapConfiguration {
    zoom: number,
    lat: number,
    lng: number
};

export const MapInitConfig: MapConfiguration = {
    zoom: 14,
    lat: 38.907808,
    lng: -77.044023
};

export let MAP_CONFIG = new InjectionToken<MapConfiguration>("map.init.config");
