import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ILocation } from './domain';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private readonly http: HttpClient) { }

  getLocations(plz: string): Observable<ILocation[]> {
    return this.http.get<ILocation[]>('/postcode/api/location/' + plz);
  }
}
