import {Injectable} from "@angular/core";
import {isNullOrUndefined} from "util";

@Injectable({
  providedIn: 'root'
})
export class CommonUtil {

  containsValue(value: any): boolean {

    switch (typeof value) {
      case 'string':
        if(isNullOrUndefined(value)) {
          return false;
        }
        if(value.trim().length > 0) {
          return true;
        }
        else {
          return false;
        }
      case 'number':
        if(isNullOrUndefined(value)) {
          return false;
        }
        else
          return true;
      default: {
        if(isNullOrUndefined(value)) {
          return false;
        }
        else
          return true;
      }
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
