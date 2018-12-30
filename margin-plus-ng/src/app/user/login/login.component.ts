import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserModel} from "../models/UserModel";
import {UserService} from "../service/user.service";
import {GlobalDataService} from "../service/global.data.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService, private globalDataService: GlobalDataService) { }

  user: UserModel = new UserModel();

  @Input()
  showModal: boolean;

  showError: boolean = false;

  @Output()
  closed: EventEmitter<boolean> = new EventEmitter();

  @Output()
  loginStatus: EventEmitter<boolean> = new EventEmitter();

  ngOnInit() {
  }

  close() {
    this.closed.emit(false);
  }

  login() {
    const username = this.user.userName;
    const password = this.user.password;
    console.log('Username', username);
    console.log('Password', password);
    const btoaStr = "Basic " + btoa(username+":"+password);
    this.userService.login(btoaStr).subscribe(data => {
      console.log("Data from server: ", data);
      this.globalDataService.userinfo = data;

      if(data != null) {
        this.loginStatus.emit(true);
      } else {
        console.log('Showing error')
        this.showError = true;
      }
    });
  }

}
