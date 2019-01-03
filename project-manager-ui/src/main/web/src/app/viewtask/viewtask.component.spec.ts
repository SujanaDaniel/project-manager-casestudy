import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserComponent } from '../updateuser/updateuser.component';
import { UpdateTaskComponent } from '../updatetask/updatetask.component';
import { FormsModule } from '@angular/forms';
import { LoaderComponent } from '../shared/loader/loader.component';
import { SearchFilterPipe } from '../shared/pipe/searchfilter.pipe';
import { AppRoutingModule } from '../app-routing.module';
import { ViewTaskComponent } from './viewtask.component';
import { UpdateProjectComponent } from '../updateproject/updateproject.component';
import { ErrorComponent } from '../shared/error/error.component';
import { SortPipe } from '../shared/pipe/sort.pipe';
import { APP_BASE_HREF } from '@angular/common';
import { ProjectService } from '../shared/service/project.service';
import { ProjectServiceMock } from '../shared/service/project.service-mock';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { of } from 'rxjs';
import { ProjManagerModel } from '../shared/model/projmanager.model';

describe('ViewTaskComponent', () => {
  let component: ViewTaskComponent;
  let fixture: ComponentFixture<ViewTaskComponent>;
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
    //spyOn(service, 'getTasks').and.returnValue(of([]));
    fixture = TestBed.createComponent(ViewTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should check taskList', () => {
    expect(component.taskList).toBeDefined;
  });
  it('should call orderBy method', () => {
    expect(component.orderBy('string')).toBe(false);
  });
  it('should call getTasks method', () => {
    expect(component.getTasks()).toBeDefined;
  });
  it('should call endTask method', () => {
    let projManagerModel = new ProjManagerModel();
    expect(component.endTask(projManagerModel)).toBeDefined;
  });
  it('should call editTask method', () => {
    let projManagerModel = new ProjManagerModel();
    expect(component.editTask(projManagerModel)).toBeDefined;
  });
  it('should call getProjects method', () => {
    expect(component.getProjects()).toBeDefined;
  });
  it('should call selectProject method', () => {
    let projManagerModel = new ProjManagerModel();
    expect(component.selectProject(projManagerModel)).toBeDefined;
  });
});
