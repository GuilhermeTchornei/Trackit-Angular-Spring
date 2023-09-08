import { Component } from '@angular/core';
import Signin from './interfaces/signin.interface';
import { ApiService } from 'src/app/config/api.service';
import { Router } from '@angular/router';
import Token from './interfaces/token.interface';

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

  constructor(public api: ApiService, private router: Router) {}

  onSubmit(){
    this.api.post<Signin, Token>("signin", this.formData).subscribe(response => {
      localStorage.setItem("token", response.token);
      this.router.navigate(["/habits"]);
    });
  }
}
