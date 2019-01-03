import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ProjManagerModel } from '../shared/model/projmanager.model';
import { ProjectService } from '../shared/service/project.service';

@Component({
  selector: 'updateuser',
  templateUrl: './updateuser.component.html',
  styleUrls: ['./updateuser.component.css']
})
export class UpdateUserComponent implements OnInit {

  @ViewChild('userForm') userForm : NgForm;
  projManagerModel : ProjManagerModel = new ProjManagerModel();
  userAction: string;
  userSearch: string;
  field: string;
  order: number = 1;
  loader: boolean;
  hasUsers: boolean;
  userList: any = [];
  
  constructor(private projectService: ProjectService, private router: Router){}

  ngOnInit(){
    this.projectService.editTask = null;
    this.loader = true;
    this.userAction = 'add';
    this.getUsersCall();
  }

  getUsersCall(): any {
    this.userList = [];
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
  }

  updateUserCall(projManagerModel: ProjManagerModel): any {
    this.loader = true;
    this.projectService.updateUser(projManagerModel).subscribe((data: any) => {
      this.loader = false;
      if(data){  
        if(this.userAction === 'add'){
          document.getElementById('addSuccessModal').click();
        } else if(this.userAction === 'update'){
          document.getElementById('updateSuccessModal').click();
        } else if(this.userAction === 'end'){
          document.getElementById('deleteSuccessModal').click();
        }
      } else {
        document.getElementById('failureModal').click();
      }
    },(error: Error) =>{
       this.loader = false;
       this.router.navigate(['/error']);
    });
  }

  updateUser(projManagerModel : ProjManagerModel): any {
    projManagerModel.action = this.userAction === 'add' ? 'add' : 'update';
    this.updateUserCall(projManagerModel);
  }
  
  editUser(user : ProjManagerModel) : any {
    this.projManagerModel.firstName = user.firstName;
    this.projManagerModel.lastName = user.lastName;
    this.projManagerModel.employeeId = user.employeeId;
    this.projManagerModel.userId = user.userId;
    this.userAction = 'update';
  }

  deleteUser(user : ProjManagerModel) : any {
    user.action = 'end';
    this.userAction = 'end';
    this.updateUserCall(user);
  }

  modalRefresh() : any {
    this.loader = true;
    this.userList = [];
    this.getUsersCall();  
    this.resetForm(); 
    this.userAction = 'add';
  }

  orderBy(value: string) : any {
    this.order = this.order * (-1);
    let order_val = this.order == 1 ? 'asc' : 'desc';
    this.field = value + "-" + order_val;
    return false;
  }

  resetForm() : any {
    this.projManagerModel = new ProjManagerModel();
    this.userForm.form.markAsPristine();
    this.userForm.form.markAsUntouched();
    this.loader = false;
  }

}
