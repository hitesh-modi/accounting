import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {StateModel} from "../../../user/models/StateModel";
import {AppModuleConfig} from "../../../app.module.config";

@Injectable({
  providedIn: 'root'
})
export class StateService {

  constructor(private httpClient: HttpClient, private appConfig: AppModuleConfig) {
  }

  getStates(): Observable<Array<StateModel>> {
    return this.httpClient.get<Array<StateModel>>(this.appConfig.getStatesURL());
  }

}
