import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user=new User();
  msg='';
  required:any;


  ngOnInit(): void {
  }
  loginuser(){
    this.userService.loginUserFromRemote(this.user).subscribe(data =>
      {console.log("respose recived");
        this.router.navigate(['/loginsuccess']);
      },
    error =>{console.log("exception occurs");
    this.msg="Bad crediancials,pls enter valid emialId and password";
    }
    )
  }

  gotoregistration(){
    this.router.navigate(['/registration']);
  }

}
