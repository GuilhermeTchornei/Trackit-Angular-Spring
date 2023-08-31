import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HabitsComponent } from './habits/habits.component';

const routes: Routes = [
  { path: 'habits', component: HabitsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HabitsRoutingModule { }
