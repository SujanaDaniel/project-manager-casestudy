import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { ProjManagerModel } from '../model/projmanager.model';
import { environment } from '../../../environments/environment';

@Injectable()
export class ProjectService {

  editTask: ProjManagerModel;
  
  constructor(private http: HttpClient) {}

  public getTasks() {
    return this.http.get<ProjManagerModel[]>(environment.API+'get/tasks');
  }

  public getTasksForProject(projectId) {
    return this.http.get<ProjManagerModel[]>(environment.API+'get/task/'+projectId);
  }
  
  public getParentTasks() {
    return this.http.get<ProjManagerModel[]>(environment.API+'get/parenttasks');
  }

  public updateTask(projManagerModel) {
    return this.http.post<ProjManagerModel>(environment.API+'update/task', projManagerModel);
  }

  public getProjects() {
    return this.http.get<ProjManagerModel[]>(environment.API+'get/projects');
  }

  public updateProject(projManagerModel) {
    return this.http.post<ProjManagerModel>(environment.API+'update/project', projManagerModel);
  }

  public getUsers() {
    return this.http.get<ProjManagerModel[]>(environment.API+'get/users');
  }

  public getUserDetailsForProject(projectId) {
    return this.http.get<ProjManagerModel>(environment.API+'get/user/'+projectId);
  }

  public updateUser(projManagerModel) {
    return this.http.post<ProjManagerModel>(environment.API+'update/user', projManagerModel);
  }

}
