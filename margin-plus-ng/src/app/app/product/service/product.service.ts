import {Injectable} from '@angular/core';
import {AppModuleConfig} from "../../../app.module.config";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {ProductModel} from "../model/ProductModel";
import {HSNSectionModel} from "../model/HSNSectionModel";
import {HSNChapterModel} from "../model/HSNChapterModel";
import {HSNModel} from "../model/HSNModel";
import {SACHeadingModel} from "../model/SACHeadingModel";
import {SACGroupModel} from "../model/SACGroupModel";
import {SacCodeModel} from "../model/SacCodeModel";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private appConfig: AppModuleConfig, private httpClient: HttpClient) {
  }

  getProducts(userid: string): Observable<Array<ProductModel>> {
    const options =
      {params: new HttpParams().set('userid', userid)};
    return this.httpClient.get<Array<ProductModel>>(this.appConfig.getProductReportURL(), options);
  }

  getHSNSections(): Observable<Array<HSNSectionModel>> {
    return this.httpClient.get<Array<HSNSectionModel>>(this.appConfig.getHSNSectionURL());
  }

  getHSNChapters(sectionId: string): Observable<Array<HSNChapterModel>> {
    let params: HttpParams = new HttpParams().set('sectionId',sectionId);
    return this.httpClient.get<Array<HSNChapterModel>>(this.appConfig.getHSNChapterURL(), {params: params});
  }

  getHSNCodes(chapterId: string): Observable<Array<HSNModel>> {
    let httpParams: HttpParams = new HttpParams().set('chapterId', chapterId);
    return this.httpClient.get<Array<HSNModel>>(this.appConfig.getHSNCodesURL(), {params: httpParams});
  }

  getSACHeadings(): Observable<Array<SACHeadingModel>> {
    return this.httpClient.get<Array<SACHeadingModel>>(this.appConfig.getSACHeadingsURL());
  }

  getSACGroups(headingId: string): Observable<Array<SACGroupModel>> {
    let httpParams: HttpParams = new HttpParams().set('headingId', headingId);
    return this.httpClient.get<Array<SACGroupModel>>(this.appConfig.getSACGroupsURL(), {params: httpParams});
  }

  getSACCodes(groupId: string): Observable<Array<SacCodeModel>> {
    let httpParams: HttpParams = new HttpParams().set('groupId', groupId);
    return this.httpClient.get<Array<SacCodeModel>>(this.appConfig.getSACCodesURL(), {params: httpParams});
  }

  submitProduct(product, userid): Observable<any> {
    let httpParam: HttpParams = new HttpParams().set('userid', userid);
    let httpHeaders: HttpHeaders = new HttpHeaders().set('content-type', 'application/json');
    return this.httpClient.post(this.appConfig.getSubmitProductURL(), JSON.stringify(product), {headers: httpHeaders, params: httpParam});
  }

}
