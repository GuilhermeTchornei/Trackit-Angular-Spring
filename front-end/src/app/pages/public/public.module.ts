import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PublicRoutingModule } from './public-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { PrimaryInputComponent } from 'src/app/sharedComponents/primary-input/primary-input.component';
import { PrimaryButtonComponent } from 'src/app/sharedComponents/primary-button/primary-button.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    LayoutComponent,
    SignupComponent,
    SigninComponent,
  ],
  imports: [
    CommonModule,
    PublicRoutingModule,
    PrimaryInputComponent,
    PrimaryButtonComponent,
    FormsModule
  ],
})
export class PublicModule { }
