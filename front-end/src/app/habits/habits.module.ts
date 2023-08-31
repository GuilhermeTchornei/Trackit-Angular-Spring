import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HabitsRoutingModule } from './habits-routing.module';
import { HabitsComponent } from './habits/habits.component';


@NgModule({
  declarations: [
    HabitsComponent,
  ],
  imports: [
    CommonModule,
    HabitsRoutingModule
  ]
})
export class HabitsModule { }
