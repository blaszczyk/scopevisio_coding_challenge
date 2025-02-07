import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IPraemienAnfrageRequest, IPraemienAnfrageResponse } from './domain';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PraemienService {

  constructor(private readonly http: HttpClient) { }

  postAnfrage(request: IPraemienAnfrageRequest): Observable<IPraemienAnfrageResponse> {
    return this.http.post<IPraemienAnfrageResponse>('/praemien/api/anfrage', request);
  }
}
