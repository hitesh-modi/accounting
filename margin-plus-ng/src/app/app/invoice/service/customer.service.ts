import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {StateModel} from "../../../user/models/StateModel";
import {AppModuleConfig} from "../../../app.module.config";
import {Customer} from "../model/Customer";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private httpClient: HttpClient, private appConfig: AppModuleConfig) {
  }

  getCustomers(userid: string): Observable<Array<Customer>> {
    let httpParams: HttpParams = new HttpParams().set('userid',userid);
    return this.httpClient.get<Array<Customer>>(this.appConfig.getCustomersURL(), {params: httpParams});
  }

}
