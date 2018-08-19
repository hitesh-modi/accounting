import { Component, OnInit } from '@angular/core';
import {InvoiceReportModel} from "./model/InvoiceReportModel";
import {InvoiceService} from "./service/invoice.service";
import {InvoiceReportRequest} from "./model/InvoiceReportRequest";
import {isNullOrUndefined} from "util";
import {DatePipe} from "@angular/common";
import {UserService} from "../../user/service/user.service";
import {GlobalDataService} from "../../user/service/global.data.service";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

constructor() {}

  ngOnInit(){

  }

}
