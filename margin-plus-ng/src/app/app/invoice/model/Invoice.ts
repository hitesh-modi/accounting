import {Customer} from "./Customer";
import {Consignee} from "./Consignee";
import {InvoiceItem} from "./InvoiceItem";

export class Invoice {
  public invoiceNumber: string;
  public invoiceDate: Date;
  public invoiceDueDate: Date;
  public customer: Customer;
  public consignee: Consignee;
  public invoiceItemDetails: Array<InvoiceItem>;
  public grandTotal: number;
  public totalTax: number;
  public amountReceived: number;
  public netTotal: number;
  public newCustomer: boolean;
  public newConsignee: boolean;
}
