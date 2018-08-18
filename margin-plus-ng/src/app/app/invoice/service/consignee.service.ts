import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {StateModel} from "../../../user/models/StateModel";
import {AppModuleConfig} from "../../../app.module.config";
import {Customer} from "../model/Customer";
import {Consignee} from "../model/Consignee";

@Injectable({
  providedIn: 'root'
})
export class ConsigneeService {

  constructor(private httpClient: HttpClient, private appConfig: AppModuleConfig) {
  }

  getConsignees(userid: string): Observable<Array<Consignee>> {
    let httpParams: HttpParams = new HttpParams().set('userid',userid);
    return this.httpClient.get<Array<Consignee>>(this.appConfig.getConsigneeURL(), {params: httpParams});
  }

}
