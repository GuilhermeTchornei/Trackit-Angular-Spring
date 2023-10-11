import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from '../pages/private/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient, private authService: AuthService) { }

  private getHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      })
    }
  }

  public get<resType>(endpoint: string, authorization: boolean = true): Observable<resType> {
    return this.http.get<resType>(this.url + endpoint, authorization ? this.getHttpOptions() : undefined).pipe(catchError(this.handleError));
  }

  public post<reqType, resType>(endpoint: string, payload: reqType, authorization: boolean = true): Observable<resType> {
    return this.http.post<resType>(this.url + endpoint, payload, authorization ? this.getHttpOptions() : undefined).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error(error);
    }
    else {
      console.log(error);
    }

    return throwError(() => new Error('Algo errado aconteceu, tente novamente'));
  }
}
