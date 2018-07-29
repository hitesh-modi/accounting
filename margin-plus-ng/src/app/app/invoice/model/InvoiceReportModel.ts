export class InvoiceReportModel {
  public invoiceNumber: string;
  public invoiceId: string;
  public customerName: string;
  public consigneeName: string;
  public invoiceAmount: string;
  public balanceAmount: string;
  public invoiceDate: Date;
  public invoiceDueDate?: Date;
  public isDanger?: boolean;
}
