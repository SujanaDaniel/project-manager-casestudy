import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'error',
  templateUrl: 'error.component.html'
})
export class ErrorComponent implements OnInit {

   errorMessage: string;
   constructor(private router: Router){}

   ngOnInit() {
     this.errorMessage = 'Our Backend service seems to be unavailable at the moment. Please try again later.'
   }

   navigateToHome(){
    this.router.navigate(['/'])
   }

}