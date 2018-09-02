import { Injectable } from '@angular/core';
import {AppModuleConfig} from "../../../app.module.config";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {InvoiceReportRequest} from "../model/InvoiceReportRequest";
import {Observable} from "rxjs/Rx";
import {InvoiceReportModel} from "../model/InvoiceReportModel";
import {Invoice} from "../model/Invoice";

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  constructor(private appConfig: AppModuleConfig, private httpClient: HttpClient) { }

  getInvoices(invoiceRequest: InvoiceReportRequest): Observable<Array<InvoiceReportModel>> {
    return this.httpClient.post<Array<InvoiceReportModel>>(this.appConfig.getInvoiceReportURL(), invoiceRequest);
  }

  createInvoice(invoice: Invoice, userid: string): Observable<any>{
    let httpParam: HttpParams = new HttpParams().set('userId', userid);
    let httpHeaders: HttpHeaders = new HttpHeaders().set('content-type', 'application/json');
    return this.httpClient.post<any>(this.appConfig.getCreateInvoiceURL(), JSON.stringify(invoice), {headers: httpHeaders, params: httpParam});
  }

}
