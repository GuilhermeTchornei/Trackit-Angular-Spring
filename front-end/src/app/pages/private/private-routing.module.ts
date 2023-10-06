import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { AuthGuard } from './auth/auth.guard';
import { HabitsComponent } from './habits/habits.component';
import { TodayComponent } from './today/today.component';
import { HistoryComponent } from './history/history.component';

const routes: Routes = [
  {
    path: '', component: LayoutComponent, canActivate: [AuthGuard], children: [
      { path: 'habits', component: HabitsComponent },
      { path: 'hoje', component: TodayComponent },
      { path: 'history', component: HistoryComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrivateRoutingModule { }
