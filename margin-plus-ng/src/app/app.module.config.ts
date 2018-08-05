import {Injectable} from "@angular/core";

@Injectable(
  {
    providedIn: 'root'
  }
)
export class AppModuleConfig {

  private BASE_URL = "http://localhost:8080"
  private INVOICES_REPORT = "/services/getInvoiceReport";
  private PRODUCT_REPORT = "/services/getProducts";

  getInvoiceReportURL(): string {
    return this.BASE_URL + this.INVOICES_REPORT;
  }

  getProductReportURL(): string {
    return this.BASE_URL + this.PRODUCT_REPORT;
  }


}
