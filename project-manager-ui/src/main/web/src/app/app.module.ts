import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { UpdateProjectComponent } from './updateproject/updateproject.component';
import { UpdateTaskComponent } from './updatetask/updatetask.component';
import { UpdateUserComponent } from './updateuser/updateuser.component';
import { ViewTaskComponent } from './viewtask/viewtask.component';
import { ErrorComponent } from './shared/error/error.component';
import { LoaderComponent } from './shared/loader/loader.component';
import { ProjectService } from './shared/service/project.service';
import { SearchFilterPipe } from './shared/pipe/searchfilter.pipe';
import { SortPipe } from './shared/pipe/sort.pipe';

@NgModule({
  declarations: [
    AppComponent,
    UpdateProjectComponent,
    UpdateTaskComponent,
    UpdateUserComponent,
    ViewTaskComponent,
    ErrorComponent,
    LoaderComponent,
    SearchFilterPipe,
    SortPipe
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [ProjectService],
  bootstrap: [AppComponent]
})
export class AppModule { }
