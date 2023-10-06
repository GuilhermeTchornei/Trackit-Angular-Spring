import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './pages/private/auth/auth.guard';






const routes: Routes = [
  {
    path: '', children: [
      {
        path: '', loadChildren:
          () => import('./pages/public/public.module')
            .then(m => m.PublicModule)
      },
      {
        path: '', canActivate: [AuthGuard], loadChildren:
          () => import('./pages/private/private.module')
            .then(m => m.PrivateModule)
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
