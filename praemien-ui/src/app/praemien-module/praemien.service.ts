import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IPraemienAntragRequest, IPraemienAntragResponse, IPraemienAntragSummary } from './domain';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PraemienService {

  constructor(private readonly http: HttpClient) { }

  postAntrag(request: IPraemienAntragRequest): Observable<IPraemienAntragResponse> {
    return this.http.post<IPraemienAntragResponse>('/praemien/api/antrag', request);
  }

  getSummary(id: string): Observable<IPraemienAntragSummary> {
    return this.http.get<IPraemienAntragSummary>('/praemien/api/summary/' + id);
  }
}
