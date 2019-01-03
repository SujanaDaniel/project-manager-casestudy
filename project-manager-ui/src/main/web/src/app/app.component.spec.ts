import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ViewTaskComponent } from './viewtask/viewtask.component';
import { UpdateTaskComponent } from './updatetask/updatetask.component';
import { UpdateUserComponent } from './updateuser/updateuser.component';
import { UpdateProjectComponent } from './updateproject/updateproject.component';
import { ErrorComponent } from './shared/error/error.component';
import { FormsModule } from '@angular/forms';
import { SortPipe } from './shared/pipe/sort.pipe';
import { SearchFilterPipe } from './shared/pipe/searchfilter.pipe';
import { LoaderComponent } from './shared/loader/loader.component';
import { APP_BASE_HREF } from '@angular/common';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        ViewTaskComponent,
        UpdateTaskComponent,
        UpdateUserComponent,
        UpdateProjectComponent,
        ErrorComponent,
        SortPipe,
        SearchFilterPipe,
        LoaderComponent
      ],
      imports:[
        FormsModule,
        AppRoutingModule
      ],
      providers: [{provide: APP_BASE_HREF, useValue : '/' }]
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'Project Manager'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Project Manager');
  }));
});
