import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTaskComponent } from './updatetask.component';
import { FormsModule } from '@angular/forms';
import { LoaderComponent } from '../shared/loader/loader.component';
import { SearchFilterPipe } from '../shared/pipe/searchfilter.pipe';
import { AppRoutingModule } from '../app-routing.module';
import { ViewTaskComponent } from '../viewtask/viewtask.component';
import { UpdateUserComponent } from '../updateuser/updateuser.component';
import { UpdateProjectComponent } from '../updateproject/updateproject.component';
import { ErrorComponent } from '../shared/error/error.component';
import { SortPipe } from '../shared/pipe/sort.pipe';
import { APP_BASE_HREF } from '@angular/common';
import { ProjectService } from '../shared/service/project.service';
import { ProjectServiceMock } from '../shared/service/project.service-mock';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';

describe('UpdateTaskComponent', () => {
  let component: UpdateTaskComponent;
  let fixture: ComponentFixture<UpdateTaskComponent>;
  let service: ProjectService;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        UpdateTaskComponent,
        LoaderComponent,
        SearchFilterPipe,
        ViewTaskComponent,
        UpdateUserComponent,
        UpdateProjectComponent,
        ErrorComponent,
        SortPipe
      ],
      imports: [
        FormsModule,
        AppRoutingModule,
        HttpClientModule
      ],
      providers: [{ provide: APP_BASE_HREF, useValue: '/' },
      { provide: ProjectService, useClass: ProjectServiceMock },
        DatePipe
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    service = TestBed.get(ProjectService);
    fixture = TestBed.createComponent(UpdateTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should call updateTask', () => {
    expect(component.updateTask(component.projManagerModel)).toBeDefined;
  });
  it('should call resetForm', () => {
    expect(component.resetForm()).toBeDefined;
  });
  it('should call selectUser', () => {
    expect(component.selectUser(component.projManagerModel)).toBeDefined;
  });
  it('should call selectProject', () => {
    expect(component.selectProject(component.projManagerModel)).toBeDefined;
  });
  it('should call selectParentTask', () => {
    expect(component.selectParentTask(component.projManagerModel)).toBeDefined;
  });
  it('should call getUsers', () => {
    expect(component.getUsers()).toBeDefined;
  });
  it('should call getProjects', () => {
    expect(component.getProjects()).toBeDefined;
  });
  it('should call getParentTasks', () => {
    expect(component.getParentTasks()).toBeDefined;
  });
});
