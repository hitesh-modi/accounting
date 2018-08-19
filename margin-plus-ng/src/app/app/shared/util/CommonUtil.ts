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

  isValidGstin(gstin: string) {

    if(isNullOrUndefined(gstin)) {
      return true;
    }
    if(gstin.trim().length == 0) {
      return true;
    }
    if(gstin.trim().length > 0) {
      let regExp = new RegExp("^[0-9]{2}[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}[0-9]{1}[A-Za-z]{1}[0-9]{1}$");
      return regExp.test(gstin);
    }

  }

}
