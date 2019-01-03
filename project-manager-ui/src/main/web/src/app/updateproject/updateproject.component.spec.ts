import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserComponent } from '../updateuser/updateuser.component';
import { UpdateTaskComponent } from '../updatetask/updatetask.component';
import { FormsModule } from '@angular/forms';
import { LoaderComponent } from '../shared/loader/loader.component';
import { SearchFilterPipe } from '../shared/pipe/searchfilter.pipe';
import { AppRoutingModule } from '../app-routing.module';
import { ViewTaskComponent } from '../viewtask/viewtask.component';
import { UpdateProjectComponent } from './updateproject.component';
import { ErrorComponent } from '../shared/error/error.component';
import { SortPipe } from '../shared/pipe/sort.pipe';
import { APP_BASE_HREF } from '@angular/common';
import { ProjectService } from '../shared/service/project.service';
import { ProjectServiceMock } from '../shared/service/project.service-mock';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { of } from 'rxjs';

describe('UpdateProjectComponent', () => {
  let component: UpdateProjectComponent;
  let fixture: ComponentFixture<UpdateProjectComponent>;
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
      imports:[
        FormsModule,
        AppRoutingModule,
        HttpClientModule
      ],
      providers: [{provide: APP_BASE_HREF, useValue : '/' },
      { provide: ProjectService, useClass: ProjectServiceMock },
      DatePipe
        ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    service = TestBed.get(ProjectService);
    spyOn(service, 'getProjects').and.returnValue(of([]));
    fixture = TestBed.createComponent(UpdateProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should call updateProject', () => {
    // component.projManagerModel = {
    //   "projectId": "0",
    //   "projectName": "",
    //   "totalNoOfTasks": 0,
    //   "noOfTasksCompleted": 0,
    //   "startDate": new Date(),
    //   "endDate": new Date(new Date().getTime() + (1000 * 60 * 60 * 24)),
    //   "priority": 0,
    //   "userId": 0,
    //   "userName": ""
    // };
    expect(component.updateProject(component.projManagerModel)).toBeDefined;
  });
  it('should call resetForm', () => {
    expect(component.resetForm()).toBeDefined;
  });
  it('should call getProjects', () => {
    expect(component.getProjects()).toBeDefined;
  });
  it('should call updateProject', () => {
    expect(component.updateProject(component.projManagerModel)).toBeDefined;
  });
  it('should call updateAProject', () => {
    expect(component.updateAProject(component.projManagerModel)).toBeDefined;
  });
  it('should call orderBy', () => {
    expect(component.orderBy('String')).toBe(false);
  });
  it('should call selectUser', () => {
    expect(component.selectUser(component.projManagerModel)).toBeDefined;
  });
});
