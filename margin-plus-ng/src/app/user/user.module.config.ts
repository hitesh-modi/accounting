import {Injectable} from "@angular/core";

@Injectable(
  {
    providedIn: 'root'
  }
)
export class UserModuleConfig {

  private BASE_URL = "http://localhost:8080"
  private GET_USER_INFO_API = "/getUser";
  private GET_USER_AUTHENCTICATED_API= "/isUserAuthenticated";
  private LOGIN_USER_API="/login";

  getUserApi(): string {
    return this.BASE_URL + this.GET_USER_INFO_API;
  }

  getLoginApi(): string {
    return this.BASE_URL + this.LOGIN_USER_API;
  }

  getUserAuthApi(): string {
    return this.BASE_URL + this.GET_USER_AUTHENCTICATED_API;
  }


}
