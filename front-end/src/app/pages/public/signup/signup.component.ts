import { Component } from '@angular/core';
import Signup from './interfaces/signup.interface';
import { ApiService } from 'src/app/config/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  formData: Signup = {
    name: '',
    email: '',
    password: '',
    photo: ''
  };

  constructor(public api: ApiService, private router: Router) { }

  onSubmit() {
    this.api.post<Signup, null>("users", this.formData, false).subscribe(() => {
      this.router.navigate(["/"]);
    });
  }
}
