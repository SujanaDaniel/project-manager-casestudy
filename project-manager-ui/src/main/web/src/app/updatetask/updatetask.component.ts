import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ProjManagerModel } from '../shared/model/projmanager.model';
import { ProjectService } from '../shared/service/project.service';

@Component({
  selector: 'updatetask',
  templateUrl: './updatetask.component.html',
  styleUrls: ['./updatetask.component.css']
})
export class UpdateTaskComponent implements OnInit {

  @ViewChild('taskForm') taskForm : NgForm;
  projManagerModel: ProjManagerModel = new ProjManagerModel();
  taskAction: string;
  projSearch: string;
  parentSearch: string;
  loader: boolean;
  hasProjects: boolean;
  hasParentTasks: boolean;
  hasUsers: boolean;
  dateErrorFlag: boolean;
  projectList: any = [];
  parentTaskList: any = [];
  userList: any = [];
  editTask: any = {};

  constructor(private router: Router, private projectService: ProjectService){}

  ngOnInit(){
    this.taskAction = 'add';
    this.projManagerModel.priority = 0;
    this.editTask = this.projectService.editTask;
    if(null !== this.editTask && undefined !== this.editTask){
      this.taskAction = 'update';
      this.projManagerModel = this.editTask;
      this.projManagerModel.project = this.editTask.projectName;
    }
  }

  updateTask(projManagerModel: ProjManagerModel): any {
    this.loader = true;
    this.projectService.updateTask(projManagerModel).subscribe((data: any) => {
      this.loader = false;
      if(data){
        if(this.taskAction === 'add'){
          if(projManagerModel.enableParentTask){
            document.getElementById('addPTaskSuccessModal').click();
          } else {
            document.getElementById('addTaskSuccessModal').click();
          }
        } else if(this.taskAction === 'update'){
          document.getElementById('updateSuccessModal').click();
        }
      }else{
        document.getElementById('failureModal').click();
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    }); 
  }

  getProjects(): any {
    this.projSearch = '';
    this.projectService.getProjects().subscribe((data: any) => {
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

  getParentTasks(): any {
    this.projectService.getParentTasks().subscribe((data: any) => {
      this.parentTaskList = data;
      document.getElementById('parentTaskModal').click();
      if(this.parentTaskList.length > 0){
        this.hasParentTasks = true;   
      } else {
        this.hasParentTasks = false;
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    });
  }

  getUsers(): any {
    this.projectService.getUsers().subscribe((data: any) => {
      this.userList = data;
      document.getElementById('userModal').click();
      if(this.userList.length > 0){
        this.hasUsers = true;   
      } else {
        this.hasUsers = false;
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    });
  }

  selectProject(project: ProjManagerModel){
    this.projManagerModel.project = project.project;
    this.projManagerModel.projectId = project.projectId;
    document.getElementById('projectModal').click();
  }

  selectParentTask(parentTask: ProjManagerModel){
    this.projManagerModel.parentTask = parentTask.parentTask;
    this.projManagerModel.parentId = parentTask.parentId;
    document.getElementById('parentTaskModal').click();
  }

  selectUser(user: ProjManagerModel){
    this.projManagerModel.userName = user.firstName + ' ' + user.lastName;
    this.projManagerModel.userId = user.userId;
    document.getElementById('userModal').click();
  }

  navigateToView(): any{
    this.router.navigate(['/viewtask']);
  }

  compareStartEndDate() : any {
    if(new Date(this.projManagerModel.endDate) < new Date(this.projManagerModel.startDate)){
       this.dateErrorFlag = true;
    } else {
       this.dateErrorFlag = false;
    }
  }

  isParentTask(enableParentTask: boolean): any {
    this.projManagerModel.priority = 0;
    this.projManagerModel.parentTask = '';
    this.projManagerModel.startDate = '';
    this.projManagerModel.endDate = '';
    this.projManagerModel.userName = '';
    this.taskForm.controls['startDate'].markAsUntouched();
    this.taskForm.controls['endDate'].markAsUntouched();
    this.taskForm.controls['startDate'].markAsPristine();
    this.taskForm.controls['endDate'].markAsPristine();
    this.dateErrorFlag = false;
  }

  resetForm() : any {
    this.projManagerModel = new ProjManagerModel();
    this.projManagerModel.priority = 0;
    this.taskForm.form.markAsPristine();
    this.taskForm.form.markAsUntouched();
    this.loader = false;
    this.taskAction = 'add';
  }
  
}
