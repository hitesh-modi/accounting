import { Injectable } from '@angular/core';
import {AppModuleConfig} from "../../../app.module.config";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {ProductModel} from "../model/ProductModel";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private appConfig: AppModuleConfig, private httpClient: HttpClient) { }

  getProducts(userid: string): Observable<Array<ProductModel>> {
    const options =
      { params: new HttpParams().set('userid', userid) } ;
    return this.httpClient.get<Array<ProductModel>>(this.appConfig.getProductReportURL(), options);
  }

}
