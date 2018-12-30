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


  public setTotal(): void {
    this.total = this.quantity * this.rate;
    this.calculateTaxableValue();
  }


  public setDiscount(): void {
    this.calculateTaxableValue();
  }

  private calculateTaxableValue(): void {
    this.taxableValue = this.total - this.discount;
    this.calculateTaxes();

  }

  private calculateTaxes(): void {
    this.cgstAmount = this.taxableValue * this.cgstRate / 100;
    this.sgstAmount = this.taxableValue * this.sgstRate / 100;
    this.igstAmount = this.taxableValue * this.igstRate / 100;
  }

}
