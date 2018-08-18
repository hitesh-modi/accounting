import {Injectable} from "@angular/core";
import {isNullOrUndefined} from "util";

@Injectable({
  providedIn: 'root'
})
export class CommonUtil {

  containsValue(stringValue: string): boolean {

    console.log('Contains Value called for ', stringValue);

    if(isNullOrUndefined(stringValue)) {
      return false;
    }

    if(stringValue.trim().length > 0) {
      return true;
    }
    else {
      return false;
    }

  }

}
