
import {Injectable, isDevMode} from "@angular/core";
import {UserService} from "../../user/service/user.service";
import {GlobalDataService} from "../../user/service/global.data.service";

@Injectable()
export class AppInitializerService {

  constructor(private userService: UserService, private globalDataService: GlobalDataService) {}

  initaializeApp(): Promise<any> {

    let promise: Promise<any> = new Promise(null);

    if(isDevMode()) {
      this.globalDataService.userAuthenticated = true;
    }

    else {
      let promise = this.userService.getIsUserAuthenticated().toPromise().then(
        onloadeddata => {
          this.globalDataService.userAuthenticated = onloadeddata;
        }
      );
    }


    return promise;
  }

}
