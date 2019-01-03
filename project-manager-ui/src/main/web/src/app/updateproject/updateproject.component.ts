import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';

import { ProjManagerModel } from '../shared/model/projmanager.model';
import { ProjectService } from '../shared/service/project.service';

@Component({
  selector: 'updateproject',
  templateUrl: './updateproject.component.html',
  styleUrls: ['./updateproject.component.css']
})
export class UpdateProjectComponent implements OnInit {

  @ViewChild('projectForm') projectForm : NgForm;
  projManagerModel: ProjManagerModel = new ProjManagerModel();
  errorFlag: boolean;
  loader: boolean;
  hasUsers: boolean;
  hasProjects: boolean;
  userList: any = [];
  projectList: any = [];
  projAction: string;
  managerSearch: string;
  projSearch: string;
  field: string;
  order: number = 1;

  constructor(private projectService: ProjectService, private router: Router){}

  ngOnInit(){
    this.projectService.editTask = null;
    this.projAction = 'add';
    this.loader = true;
    this.projManagerModel.hasStartEndDate = false;
    this.projManagerModel.priority = 0;
    this.userList = []; 
    this.projectList = [];

    this.projectService.getUsers().subscribe((data: any) => {
      this.loader = false;
      this.userList = data;
      if(this.userList.length > 0){
        this.hasUsers = true;
      } else {
        this.hasUsers = false; 
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    }); 

    this.getProjects();

  }

  getProjects() : any {
    this.projAction = 'add';
    this.loader = true;
    this.projectService.getProjects().subscribe((data: any) => {
      this.loader = false;
      this.projectList = data;
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

  getUserDetailsForProject(project : ProjManagerModel): any {
    this.projectService.getUserDetailsForProject(project.projectId).subscribe((data: any) => {
      this.loader = false;
      if(null !== data){
        this.projManagerModel.userName = data.userName;
        this.projManagerModel.userId = data.userId;
        if(this.projAction === 'end'){
          this.updateProject(project);
        }
      }else{
        document.getElementById('failureModal').click();
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    });
  }

  updateProject(projManagerModel : ProjManagerModel): any {
    this.loader = true;
    projManagerModel.action = this.projAction;
    projManagerModel.userId = this.projManagerModel.userId;
    this.projectService.updateProject(projManagerModel).subscribe((data: any) => {
      this.loader = false;
      if(data){
        if(this.projAction === 'add'){
          document.getElementById('addSuccessModal').click();
        } else if(this.projAction === 'update'){
          document.getElementById('updateSuccessModal').click();
        } else if(this.projAction === 'end'){
          document.getElementById('deleteSuccessModal').click();
        }
      }else{
        document.getElementById('failureModal').click();
      }
    },(error: Error) =>{
      this.loader = false;
      this.router.navigate(['/error']);
    });
  }
  
  updateAProject(project : ProjManagerModel): any {
    this.loader = true;
    this.projAction = 'update';
    this.projManagerModel = project;
    this.projManagerModel.hasStartEndDate = true;
    this.getUserDetailsForProject(project);
  }

  suspendAProject(project : ProjManagerModel): any {
    this.projAction = 'end';
    this.getUserDetailsForProject(project);
  }

  setStartEndDate() : any {
    var datePipe = new DatePipe('en-US');
    var date = new Date ();
    this.projManagerModel.startDate = datePipe.transform(date,'yyyy-MM-dd');
    this.projManagerModel.endDate = datePipe.transform(date.getTime() + (60*60*24*1000),'yyyy-MM-dd');
    if(this.projManagerModel.hasStartEndDate){
      this.projManagerModel.startDate = '';
      this.projManagerModel.endDate = '';
    }
  }

  compareStartEndDate() : any {
    if(new Date(this.projManagerModel.endDate) < new Date(this.projManagerModel.startDate)){
       this.errorFlag = true;
    } else {
       this.errorFlag = false;
    }
  }

  searchManager(userName: string) : any {
    document.getElementById('managerModal').click();
  }

  selectUser(user: ProjManagerModel): any {
    document.getElementById('managerModal').click();
    this.projManagerModel.userName = user.firstName + ' ' + user.lastName;
    this.projManagerModel.userId = user.userId;
  }

  modalRefresh() : any {
    this.getProjects();
    this.resetForm(); 
  }
  
  resetForm() : any {
    this.projManagerModel = new ProjManagerModel();
    this.projManagerModel.priority = 0;
    this.projManagerModel.hasStartEndDate = false;
    this.projectForm.form.markAsPristine();
    this.projectForm.form.markAsUntouched();
  }

  orderBy(value: string) : any {
    this.order = this.order * (-1);
    let order_val = this.order == 1 ? 'asc' : 'desc';
    this.field = value + "-" + order_val;
    return false;
  }

}
