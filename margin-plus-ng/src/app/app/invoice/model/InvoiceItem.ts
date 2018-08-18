import {ProductModel} from "../../product/model/ProductModel";
import {TaxItem} from "./TaxItem";

export class InvoiceItem {
  public serialNumber: number;
  public product: ProductModel;
  public quantity: number;
  public unit: string;
  public rate: number;
  public total: number;
  public taxableValue: number;
  public cgstRate: number;
  public cgstAmount: number;
  public sgstRate: number;
  public sgstAmount: number;
  public igstAmount: number;
  public igstRate: number;
  public discount: number;
  public additionalTaxes: Array<TaxItem>;
}
