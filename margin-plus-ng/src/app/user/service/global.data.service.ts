import {Injectable, isDevMode} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../models/UserModel";
import {UserModuleConfig} from "../user.module.config";
import {BehaviorSubject, Observable} from "rxjs/Rx";

@Injectable({
  providedIn: 'root'
})
export class GlobalDataService {

  constructor() {
    if(isDevMode()) {
      console.log('Running in local mode');
      this.userAuthenticated = true;
    }
  }

  public userAuthenticated: boolean;

}
