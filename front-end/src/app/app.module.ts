import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { HabitsModule } from './habits/habits.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([   
    ]),
    BrowserAnimationsModule,
    MatToolbarModule,
    HabitsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
