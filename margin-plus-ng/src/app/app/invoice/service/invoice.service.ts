import { Injectable } from '@angular/core';
import {AppModuleConfig} from "../../../app.module.config";
import {HttpClient} from "@angular/common/http";
import {InvoiceReportRequest} from "../model/InvoiceReportRequest";
import {Observable} from "rxjs/Rx";
import {InvoiceReportModel} from "../model/InvoiceReportModel";

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  constructor(private appConfig: AppModuleConfig, private httpClient: HttpClient) { }

  getInvoices(invoiceRequest: InvoiceReportRequest): Observable<Array<InvoiceReportModel>> {
    return this.httpClient.post<Array<InvoiceReportModel>>(this.appConfig.getInvoiceReportURL(), invoiceRequest);
  }

}
