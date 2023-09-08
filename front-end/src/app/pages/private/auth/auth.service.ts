import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import TokenPayload from './tokenPayload.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: string|null = localStorage.getItem('token');

  constructor(private jwtHelper: JwtHelperService) { }

  public getPayload(): string|null {
    if(!this.token) return null;
    return this.jwtHelper.decodeToken<TokenPayload>(this.token)?.email || null;
  }

  public logout(): void {
    localStorage.removeItem("token");
  }

  public isLoggedIn(): boolean{
    return !this.jwtHelper.isTokenExpired(this.token);
  }
}
