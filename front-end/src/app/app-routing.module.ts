import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';






const routes: Routes = [
  { path: '', children:[
    { path: '', loadChildren: 
        () => import('./pages/public/public.module')
            .then(m => m.PublicModule) 
    },
    { path: 'habits', loadChildren: 
    () => import('./pages/private/private.module')
        .then(m => m.PrivateModule) }
  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
