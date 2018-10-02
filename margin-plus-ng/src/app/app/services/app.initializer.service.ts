
import {Injectable, isDevMode} from "@angular/core";
import {UserService} from "../../user/service/user.service";
import {GlobalDataService} from "../../user/service/global.data.service";
import {UserModel} from "../../user/models/UserModel";
import {StateModel} from "../../user/models/StateModel";

@Injectable()
export class AppInitializerService {

  constructor(private userService: UserService, private globalDataService: GlobalDataService) {}

  initaializeApp(): Promise<any> {
    return this._getUserInfo();
  }

  private _getUserInfo() {
    let promise = this.userService.getUserinfo().toPromise().then(
      onloadeddata => {
        if(isDevMode()) {
          let user: UserModel = new UserModel();
          user.userAuthenticated = true;
          user.userName = 'Hitesh Modi';
          user.userid = 'er.hiteshmodi@gmail.com';

          let state = new StateModel();
          state.statecode = '23';
          state.statename = 'Madhya Pradesh';
          user.state = state;
          this.globalDataService.userinfo = user;
        }
        else {
          this.globalDataService.userinfo = onloadeddata;
        }
      }
    );
    return promise;
  }

}
