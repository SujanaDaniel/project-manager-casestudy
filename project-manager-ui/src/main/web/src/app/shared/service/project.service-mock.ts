import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { of } from 'rxjs';

import { ProjManagerModel } from '../model/projmanager.model';

@Injectable()
export class ProjectServiceMock {

    constructor(private httpClient: HttpClient, private router: Router) { }

    getTasks(projectId) {
        let tasks = [
          
        ];
        return of(tasks);
    }

    getTasksForProject(projectId) {
        let tasks = [
          
        ];
        return of(tasks);
    }

    getParentTasks(projectId) {
        let parentTasks = [
           
        ];
        return of(parentTasks);
    }

    getUsers(inputParam) {
        let users = [];
        return of(users);
    }

    getUserDetailsForProject(projectId) {
        let projManagerModel = new ProjManagerModel()
        return of(projManagerModel);
    }

    updateUser(inputParam) {
        return of(true);
    }

    getProjects(inputParam) {
        let projects = [];
        return of(projects);
    }

    updateProject(inputParam) {    
        return of(true);
    }

    updateTask(inputParam) {
       return of(true);
    }

}
