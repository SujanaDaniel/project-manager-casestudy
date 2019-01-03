import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserComponent } from './updateuser.component';
import { UpdateTaskComponent } from '../updatetask/updatetask.component';
import { FormsModule } from '@angular/forms';
import { LoaderComponent } from '../shared/loader/loader.component';
import { SearchFilterPipe } from '../shared/pipe/searchfilter.pipe';
import { AppRoutingModule } from '../app-routing.module';
import { ViewTaskComponent } from '../viewtask/viewtask.component';
import { UpdateProjectComponent } from '../updateproject/updateproject.component';
import { ErrorComponent } from '../shared/error/error.component';
import { SortPipe } from '../shared/pipe/sort.pipe';
import { APP_BASE_HREF } from '@angular/common';
import { ProjectService } from '../shared/service/project.service';
import { ProjectServiceMock } from '../shared/service/project.service-mock';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { of } from 'rxjs';
import { NgForm, FormControl, NgModel } from '@angular/forms';

describe('UpdateUserComponent', () => {
  let component: UpdateUserComponent;
  let fixture: ComponentFixture<UpdateUserComponent>;
  let service: ProjectService;
  let userForm = <NgForm>{
    value: {
      "userId": 0,
      "firstName": "",
      "lastName": "",
      "employeeId": ""
    }
  };

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
        DatePipe,
        NgForm
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    service = TestBed.get(ProjectService);
    fixture = TestBed.createComponent(UpdateUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    userForm = fixture.debugElement.injector.get(NgForm);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should check userList', () => {
    expect(component.userList).toBeDefined;
  });
  it('should call getUsers method', () => {
    expect(component.getUsersCall()).toBeDefined;
  });
  it('should call orderBy method', () => {
    expect(component.orderBy('string')).toBe(false);
  });
  it('should call editUser method', () => {
    expect(component.editUser(component.projManagerModel)).toBeDefined;
  });
  it('should call deleteUser method', () => {
    expect(component.deleteUser(component.projManagerModel)).toBeDefined;
  });
  it('should call resetForm method', () => {
    expect(component.resetForm()).toBeDefined;
  });
  it('should call updateUser method', () => {
    expect(component.updateUser(component.projManagerModel)).toBeTruthy;
  });
});
