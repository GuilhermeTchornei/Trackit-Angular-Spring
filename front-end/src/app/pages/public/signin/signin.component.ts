import { Component } from '@angular/core';
import Signin from './interfaces/signin.interface';
import { ApiService } from 'src/app/config/api.service';
import { Router } from '@angular/router';
import Token from './interfaces/token.interface';
import { lastValueFrom } from 'rxjs';
import { AuthService } from '../../private/auth/auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent {
  formData: Signin = {
    email: '',
    password: '',
  };

  constructor(public api: ApiService, private router: Router, private authService: AuthService) { }

  async onSubmit() {
    this.authService.logout();

    const response = await lastValueFrom(this.api.post<Signin, Token>("signin", this.formData, false));
    localStorage.setItem("token", response.token);
    localStorage.setItem("photo", response.photo);
    this.router.navigate(["/habits"]);
  }
}
