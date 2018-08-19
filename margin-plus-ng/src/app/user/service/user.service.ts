import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../models/UserModel";
import {UserModuleConfig} from "../user.module.config";
import {Observable} from "rxjs/Rx";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private isUserAuthenticates$: Observable<boolean>;

  public user: UserModel;

  constructor(private httpClient: HttpClient, private userConfig: UserModuleConfig) { }

  getUserinfo(): Observable<UserModel> {
    return this.httpClient.get<UserModel>(this.userConfig.getUserApi())
  }

  getIsUserAuthenticated(): Observable<boolean> {
    console.log("Getting if the user is authenticated or not : ", this.userConfig.getUserAuthApi())
    this.isUserAuthenticates$ = this.httpClient.get<boolean>(this.userConfig.getUserAuthApi())
      .debounceTime(1000)
      .share()
      .finally(function () {
        this.isUserAuthenticates$ = null;
      })
      .catch((error:any) => Observable.throwError(error.json().error || 'Server Error'));
    return this.isUserAuthenticates$;
  }


  login(authString: string):Observable<UserModel> {
    return this.httpClient.post<UserModel>(this.userConfig.getLoginApi(), {authorization: authString});
  }

}
