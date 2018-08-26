import {
  Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges,
  ViewChild
} from '@angular/core';
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

  applicableTaxType: string = '';

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
    if(this.customer.stateCode == this.consignee.stateCode) {
      this.applicableTaxType = 'S-AND-C-GST';
    }
    else {
      this.applicableTaxType = 'I-GST';
    }
    this.invoiceWizard.next();
  }

  getCustomerPage() {

    if(this.validateInvoiceDates()) {
      this.fetchCustomers();
      this.invoiceWizard.next();
    }
  }

  getConsigneePage() {
      this.fetchConsignee();
      this.invoiceWizard.next();
  }

  toggleConsigeeOptions(option: string) {

    if(option == 'new') {
      this.consigneeOptions.newConsignee = true;
      this.consigneeOptions.existingConsignee = false;
      this.consigneeOptions.sameAsCustomer = false;
      this.clearConsignee();
    }
    else if(option == 'existing') {
      this.consigneeOptions.newConsignee = false;
      this.consigneeOptions.existingConsignee = true;
      this.consigneeOptions.sameAsCustomer = false;
    }
    else if(option == 'sameAsCustomer') {
      this.consigneeOptions.newConsignee = false;
      this.consigneeOptions.existingConsignee = false;
      this.consigneeOptions.sameAsCustomer = true;
      this.copyCustomerToConsignee();
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


  calculateTotal(newValue) {

    if(this.util.containsValue(this.tempInvoiceItem.quantity) &&
      this.util.containsValue(newValue)) {
      this.tempInvoiceItem.total = this.tempInvoiceItem.quantity * +newValue;
    }
    else {
      this.tempInvoiceItem.total = 0;
    }
  }


  setTaxRate() {
    if(this.customer.stateCode == this.consignee.stateCode) {
      this.tempInvoiceItem.cgstRate = this.tempInvoiceItem.product.taxRate / 2;
      this.tempInvoiceItem.sgstRate = this.tempInvoiceItem.product.taxRate / 2;
    } else {
      this.tempInvoiceItem.igstRate = this.tempInvoiceItem.product.taxRate;
    }

    this.tempInvoiceItem.quantity = 0;
    this.tempInvoiceItem.rate = 0;
    this.tempInvoiceItem.unit = '';
    this.tempInvoiceItem.total = 0;
    this.tempInvoiceItem.discount = 0;
    this.tempInvoiceItem.taxableValue = 0;
    this.tempInvoiceItem.cgstAmount = 0;
    this.tempInvoiceItem.sgstAmount = 0;
    this.tempInvoiceItem.igstAmount = 0;

  }

  calculateTaxableValue() {
    if(this.util.containsValue(this.tempInvoiceItem.discount) &&
    this.util.containsValue(this.tempInvoiceItem.total)) {
      this.tempInvoiceItem.taxableValue = this.tempInvoiceItem.total - this.tempInvoiceItem.discount;
    }
    else {
      this.tempInvoiceItem.taxableValue = this.tempInvoiceItem.total;
    }

    this.calculateTaxes();
  }

  calculateTaxes() {

    // TODO: Decouple the taxes calculation so that taxes other than GST can be accomodated easily

    switch (this.applicableTaxType) {
      case 'S-AND-C-GST':
        if(this.util.containsValue(this.tempInvoiceItem.sgstRate) &&
          this.util.containsValue(this.tempInvoiceItem.cgstRate) &&
          this.util.containsValue(this.tempInvoiceItem.taxableValue)) {
          this.tempInvoiceItem.cgstAmount = this.tempInvoiceItem.taxableValue * this.tempInvoiceItem.cgstRate / 100;
          this.tempInvoiceItem.sgstAmount = this.tempInvoiceItem.taxableValue * this.tempInvoiceItem.sgstRate / 100;
        }
        break;
      case 'I-GST':
        if(this.util.containsValue(this.tempInvoiceItem.igstRate) &&
          this.util.containsValue(this.tempInvoiceItem.taxableValue)) {
          this.tempInvoiceItem.igstAmount = this.tempInvoiceItem.taxableValue * this.tempInvoiceItem.igstRate / 100;
        }
        break;
    }

  }

  addItemToInvoice() {
    this.invoiceItems.push(this.tempInvoiceItem);
    this.tempInvoiceItem = new InvoiceItem();
  }

  isInvoiceFormValid(): boolean {
    if(this.util.containsValue(this.tempInvoiceItem.product) &&
      (this.util.containsValue(this.tempInvoiceItem.quantity) && (this.tempInvoiceItem.quantity > 0)) &&
    this.util.containsValue(this.tempInvoiceItem.unit) &&
      (this.util.containsValue(this.tempInvoiceItem.total) && this.tempInvoiceItem.total > 0)&&
      (this.util.containsValue(this.tempInvoiceItem.rate) && this.tempInvoiceItem.rate > 0) &&
      (this.util.containsValue(this.tempInvoiceItem.taxableValue) && this.tempInvoiceItem.taxableValue > 0)) {
      if(this.applicableTaxType == 'S-AND-C-GST') {
        if(
          (this.util.containsValue(this.tempInvoiceItem.cgstRate) && this.tempInvoiceItem.cgstRate > 0)
        && (this.util.containsValue(this.tempInvoiceItem.cgstAmount) && this.tempInvoiceItem.cgstAmount > 0)
        && (this.util.containsValue(this.tempInvoiceItem.sgstAmount) && this.tempInvoiceItem.sgstAmount > 0)
        && (this.util.containsValue(this.tempInvoiceItem.sgstRate) && this.tempInvoiceItem.sgstRate)) {
          return true;
        }
      }
      else if(this.applicableTaxType == 'I-GST') {
        if(
          (this.util.containsValue(this.tempInvoiceItem.igstAmount) && this.tempInvoiceItem.igstAmount) &&
          (this.util.containsValue(this.tempInvoiceItem.igstRate) && this.tempInvoiceItem.igstRate))
          return true;
      }
    }
    return false;
  }

}
