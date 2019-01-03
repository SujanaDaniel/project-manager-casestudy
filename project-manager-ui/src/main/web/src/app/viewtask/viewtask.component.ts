import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ProjManagerModel } from '../shared/model/projmanager.model';
import { ProjectService } from '../shared/service/project.service';

@Component({
  selector: 'viewtask',
  templateUrl: './viewtask.component.html',
  styleUrls: ['./viewtask.component.css']
})
export class ViewTaskComponent implements OnInit {
  
  projManagerModel: ProjManagerModel = new ProjManagerModel();
  field: string;
  order: number = 1;
  reverse: boolean = false;
  loader: boolean;
  hasTasks: boolean;
  hasProjects: boolean;
  taskList: any = [];
  projectList: any = [];
  
  constructor(private projectService: ProjectService, private router: Router){}

  ngOnInit(){
    this.loader = true;
    this.taskList = [];
    this.getTasks();
    
  }

  getTasks(): any {
    this.projectService.getTasks().subscribe((data: any) => {
      this.loader = false;
      this.taskList = data;
      if(this.taskList.length > 0){
        this.hasTasks = true;
      } else {
        this.hasTasks = false;
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    }); 
  }

  getProjects() : any {
    this.loader = true;
    this.projectService.getProjects().subscribe((data: any) => {
      this.loader = false;
      this.projectList = data;
      document.getElementById('projectModal').click();
      if(this.projectList.length > 0){
        this.hasProjects = true;
      } else {
        this.hasProjects = false;
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    });
  }

  selectProject(project: ProjManagerModel){
    document.getElementById('projectModal').click();
    this.loader = true;
    this.taskList = [];
    this.projManagerModel.project = project.project;
    this.projectService.getTasksForProject(project.projectId).subscribe((data: any) => {
      this.loader = false;
      if(null !== data){
        this.taskList = data;
      }else{
        document.getElementById('failureModal').click();
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    });
  }

  editTask(task: ProjManagerModel){
    this.projectService.editTask = task;
    this.router.navigate(['/updatetask']);
  }

  endTask(task: ProjManagerModel){
    this.loader = true;
    task.action = 'end';
    this.projectService.updateTask(task).subscribe((data: any) => {
      this.loader = false;
      if(data){
        document.getElementById('deleteSuccessModal').click();
      }else{
        document.getElementById('failureModal').click();
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    }); 
  }

  orderBy(value: string) : any {
    this.order = this.order * (-1);
    let order_val = this.order == 1 ? 'asc' : 'desc';
    this.field = value + "-" + order_val;
    return false;
  }

  modalRefresh(){
    this.getTasks();
  }
  
}
