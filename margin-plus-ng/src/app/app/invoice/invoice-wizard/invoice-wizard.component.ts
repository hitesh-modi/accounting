import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Invoice} from "../model/Invoice";
import {StateModel} from "../../../user/models/StateModel";
import {StateService} from "../service/state.service";
import {Customer} from "../model/Customer";
import {CustomerService} from "../service/customer.service";
import {GlobalDataService} from "../../../user/service/global.data.service";
import {Consignee} from "../model/Consignee";
import {ConsigneeService} from "../service/consignee.service";
import {InvoiceItem} from "../model/InvoiceItem";
import {ProductModel} from "../../product/model/ProductModel";
import {ProductService} from "../../product/service/product.service";
import {ClrWizard} from "@clr/angular";
import {CommonUtil} from "../../shared/util/CommonUtil";
import {isNullOrUndefined} from "util";

@Component({
  selector: 'app-invoice-wizard',
  templateUrl: './invoice-wizard.component.html',
  styleUrls: ['./invoice-wizard.component.css']
})
export class InvoiceWizardComponent implements OnInit {

  @Input("invoiceWizardOpen") wizardOpen: boolean = false;

  @Output("invoiceWizardClose") wizardClose: EventEmitter<boolean> = new EventEmitter(false);

  @ViewChild("invoiceWizard") invoiceWizard: ClrWizard;

  invoiceDateError: boolean = false;

  invoiceDueDateError: boolean = false;

  invoice: Invoice = new Invoice();

  customer: Customer = new Customer();

  consignee: Consignee = new Consignee();

  newCustomer: boolean = true;

  newConsignee: boolean = true;

  states: Array<StateModel> = new Array();

  customers: Array<Customer> = new Array();

  consignees: Array<Consignee> = new Array();

  invoiceItems: Array<InvoiceItem> = new Array();

  tempInvoiceItem: InvoiceItem = new InvoiceItem();

  sameAsCustomer: boolean = false;

  products: Array<ProductModel> = new Array();

  invoiceDateErrorMessage: string;

  showInvoiceDateError: boolean = false;

  consigneeOptions: any = {
    'newConsignee':true,
    'existingConsignee': false,
    'sameAsCustomer': false
  };

  constructor(
    private stateService: StateService,
    private customerService: CustomerService,
    private gds: GlobalDataService,
    private consigneeService: ConsigneeService,
    private productService: ProductService,
    private util: CommonUtil) { }

  ngOnInit() {
    this.stateService.getStates().subscribe(
      onloadeddata => {
        this.states = onloadeddata;
      },
      error2 => {

      }
    );
  }

  fetchCustomers(): void {
    this.customerService.getCustomers(this.gds.userinfo.userid).subscribe(
      onloadeddata => {
        this.customers = onloadeddata;
      },
      error2 => {

      }
    );
  }

  fetchConsignee(): void {
    this.sameAsCustomer = false;
    this.consigneeService.getConsignees(this.gds.userinfo.userid).subscribe(
      onloadeddata => {
        this.consignees = onloadeddata;
      },
      error2 => {

      }
    );
  }

  copyCustomerToConsignee(): void {
    if(this.consigneeOptions.sameAsCustomer) {
      this.consignee.name = this.customer.name;
      this.consignee.address = this.customer.address;
      this.consignee.gstin = this.customer.gstin;
      this.consignee.mobileNo = this.customer.mobileNo;
      this.consignee.phoneNo = this.customer.phoneNo;
      this.consignee.email = this.customer.email;
      this.consignee.stateCode = this.customer.stateCode;
    }
  }


  getProducts(): void {
    this.productService.getProducts(this.gds.userinfo.userid).subscribe(
      data => {
        this.products = data;
      },
      error2 => {

      }
    );
  }

  closeWizard() {
    this.wizardClose.emit(false);
  }

  getNextPage() {
    this.invoiceWizard.next();
  }

  getInvoiceItemPage() {
    this.getProducts();
    this.invoiceWizard.next();
  }

  getCustomerPage() {

    if(this.validateInvoiceDates()) {
      this.fetchCustomers();
      this.invoiceWizard.next();
    }
  }

  validateInvoiceDates(): boolean {
    let isFormValid = true;
    if(isNullOrUndefined(this.invoice.invoiceDate)) {
      this.invoiceDateError = true;
      isFormValid = false;
    }
    else {
      this.invoiceDateError = false;
    }

    if(isNullOrUndefined(this.invoice.invoiceDueDate)) {
      this.invoiceDueDateError = true;
      isFormValid = false;
    }
    else {
      this.invoiceDueDateError = false;
    }

    if(this.invoice.invoiceDate.getTime() > this.invoice.invoiceDueDate.getTime()) {
      this.showInvoiceDateError = true;
      this.invoiceDateErrorMessage = 'Invoice Due Date cannot be before Invoice Date';
      isFormValid = false;
    }

    return isFormValid;
  }

  checkCustomerSelected(): boolean{
    if(this.util.containsValue(this.customer.name) &&
       this.util.containsValue(this.customer.address) &&
       this.util.containsValue(this.customer.mobileNo) &&
       this.util.containsValue(this.customer.phoneNo) &&
       this.util.containsValue(this.customer.stateCode)) {
      return true;
    }
    else {
      return false;
    }
  }

  clearCustomer() {
    this.customer = new Customer();
  }

  clearConsignee() {
    this.consignee = new Consignee();
  }

  checkConsigneeSelected(): boolean {
    if(this.util.containsValue(this.consignee.name) &&
      this.util.containsValue(this.consignee.address) &&
      this.util.containsValue(this.consignee.mobileNo) &&
      this.util.containsValue(this.consignee.phoneNo) &&
      this.util.containsValue(this.consignee.stateCode)) {
      return true;
    }
    else {
      return false;
    }
  }


  calculateTotal() {
    if(this.util.containsValue(this.tempInvoiceItem.quantity.toString()) &&
      this.util.containsValue(this.tempInvoiceItem.rate.toString())) {
      this.tempInvoiceItem.total = this.tempInvoiceItem.quantity * this.tempInvoiceItem.rate;
    }
    else {
      this.tempInvoiceItem.total = 0;
    }
  }


  calculateTaxableValue() {
    if(this.util.containsValue(this.tempInvoiceItem.discount.toString()) &&
    this.util.containsValue(this.tempInvoiceItem.total.toString())) {
      this.tempInvoiceItem.taxableValue = this.tempInvoiceItem.total - this.tempInvoiceItem.discount;
    }
    else {
      this.tempInvoiceItem.taxableValue = this.tempInvoiceItem.total;
    }
  }

}
