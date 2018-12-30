import { Component, OnInit } from '@angular/core';
import {InvoiceService} from "../service/invoice.service";
import {DatePipe} from "@angular/common";
import {GlobalDataService} from "../../../user/service/global.data.service";
import {InvoiceReportModel} from "../model/InvoiceReportModel";
import {InvoiceReportRequest} from "../model/InvoiceReportRequest";
import {Invoice} from "../model/Invoice";

@Component({
  selector: 'app-invoice-report',
  templateUrl: './invoice-report.component.html',
  styleUrls: ['./invoice-report.component.css']
})
export class InvoiceReportComponent implements OnInit {

  constructor(private invoiceService: InvoiceService, private datePipe: DatePipe, private globalDataService: GlobalDataService) { }

  invoices: Array<InvoiceReportModel>;

  invoiceReportRequest: InvoiceReportRequest = new InvoiceReportRequest();

  showSearch: boolean;

  createInvoice: boolean;

  showInvoiceProgress: boolean;

  invoiceCreationSuccessMessage: string;

  showInvoiceNumber: boolean;

  collapseIcon: string = "plus";

  ngOnInit() {
    this.getInvoices();
  }

  toggleSearch() {
    this.showSearch = !this.showSearch;

  }

  getInvoices() {
    this.invoiceReportRequest.userid = this.globalDataService.userinfo.userid;
    this.invoiceService.getInvoices(this.invoiceReportRequest).subscribe(data => {
      console.table(this.invoices);
      this.invoices = data;
      this.showSearch = false;
    });

  }

  postInvoice(invoice: Invoice) {
    console.log('Creating invoice', invoice);
    this.createInvoice = false;
    this.showInvoiceProgress = true;
    this.invoiceService.createInvoice(invoice, this.globalDataService.userinfo.userid).subscribe(
      onloadeddata => {
        this.showInvoiceProgress = false;
        console.log('Invoice created successfully', onloadeddata);
        this.invoiceCreationSuccessMessage = "Invoice created successfully "+onloadeddata.response;
        this.showInvoiceNumber = true;
      },
      error2 => {
        console.log("Error", error2);
      },
    ()=> {
        console.log("Complete");
    }
    );
  }


}
