import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HabitsComponent } from './habits/components/habits.component';
import { LayoutComponent } from './layout/layout.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  { path: '', component: LayoutComponent, canActivate: [AuthGuard], children:[
    { path: '', component: HabitsComponent }
  ] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrivateRoutingModule { }
