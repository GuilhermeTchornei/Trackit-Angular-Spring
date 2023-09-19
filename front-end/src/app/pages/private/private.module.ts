import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrivateRoutingModule } from './private-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NgCircleProgressModule } from 'ng-circle-progress';
import { MatButtonModule } from '@angular/material/button';
import { HabitsComponent } from './habits/habits.component';
import { NewHabitComponent } from './habits/components/new-habit/new-habit.component';
import { HabitComponent } from './habits/components/habit/habit.component';
import { MatCardModule } from '@angular/material/card';
import { PrimaryInputComponent } from 'src/app/sharedComponents/primary-input/primary-input.component';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { FormsModule } from '@angular/forms';




@NgModule({
  declarations: [
    LayoutComponent,
    HabitsComponent,
    NewHabitComponent,
    HabitComponent,
  ],
  imports: [
    CommonModule,
    PrivateRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    PrimaryInputComponent,
    MatButtonToggleModule,
    FormsModule,
    NgCircleProgressModule.forRoot({
      radius: 100,
      outerStrokeWidth: 16,
      innerStrokeWidth: 8,
      outerStrokeColor: "#78C000",
      innerStrokeColor: "#C7E596",
      animationDuration: 300,
    }),
  ],
})
export class PrivateModule { }
