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
import { TodayComponent } from './today/today.component';
import { TodayHabitsComponent } from './today/components/today-habits/today-habits.component';
import { MatIconModule } from '@angular/material/icon';
import { TodaysProgressionService } from 'src/app/services/todays-progression.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { HistoryComponent } from './history/history.component';
import { MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';


@NgModule({
  providers: [
    TodaysProgressionService,
    { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' }
  ],
  declarations: [
    LayoutComponent,
    HabitsComponent,
    NewHabitComponent,
    HabitComponent,
    TodayComponent,
    TodayHabitsComponent,
    HistoryComponent,
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
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
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
