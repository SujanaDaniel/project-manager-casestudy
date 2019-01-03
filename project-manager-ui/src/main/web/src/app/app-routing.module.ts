import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UpdateProjectComponent } from './updateproject/updateproject.component';
import { UpdateTaskComponent } from './updatetask/updatetask.component';
import { UpdateUserComponent } from './updateuser/updateuser.component';
import { ViewTaskComponent } from './viewtask/viewtask.component';
import { ErrorComponent } from './shared/error/error.component';

const routes: Routes = [
  { path: 'updateproject', component: UpdateProjectComponent},
  { path: 'updatetask', component: UpdateTaskComponent },
  { path: 'updateuser', component: UpdateUserComponent},
  { path: 'viewtask', component: ViewTaskComponent },
  { path: 'error', component: ErrorComponent },
  { path: '',   redirectTo: '/updateuser', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
