import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private url = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  public get<resType>(endpoint: string): Observable<resType> {
    return this.http.get<resType>(this.url + endpoint).pipe(catchError(this.handleError));
  }

  public post<reqType, resType>(endpoint: string, payload: reqType): Observable<resType> {
    return this.http.post<resType>(this.url + endpoint, payload).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse){
    console.log(error.status);
    if(error.status === 0) {
      console.error(error.error);
    }
    else {
      console.log(error);
    }

    return throwError(() => new Error('Algo errado aconteceu, tente novamente'));
  }
}
