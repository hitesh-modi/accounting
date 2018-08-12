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
  private HSN_SECTIONS = "/services/getHSNSections";
  private HSN_CHAPTERS = "/services/getHsnChapter";
  private HSN_CODES = "/services/getHsn";
  private SAC_HEADINGS = "/services/getSacHeadings";
  private SAC_GROUPS = "/services/getGroupsForHeading";
  private SAC_CODES = "/services/getSacsFromGroupId";
  private SUBMIT_PRODUCT = "/services/createProduct";

  getInvoiceReportURL(): string {
    return this.BASE_URL + this.INVOICES_REPORT;
  }

  getProductReportURL(): string {
    return this.BASE_URL + this.PRODUCT_REPORT;
  }

  getHSNSectionURL(): string {
    return this.BASE_URL + this.HSN_SECTIONS;
  }

  getHSNChapterURL() : string {
    return this.BASE_URL + this.HSN_CHAPTERS;
  }

  getHSNCodesURL(): string {
    return this.BASE_URL + this.HSN_CODES;
  }

  getSACHeadingsURL(): string {
    return this.BASE_URL + this.SAC_HEADINGS;
  }

  getSACGroupsURL(): string {
    return this.BASE_URL + this.SAC_GROUPS
  }

  getSACCodesURL(): string {
    return this.BASE_URL + this.SAC_CODES;
  }

  getSubmitProductURL(): string {
    return this.BASE_URL + this.SUBMIT_PRODUCT;
  }

}
