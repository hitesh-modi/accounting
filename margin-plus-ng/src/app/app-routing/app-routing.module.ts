import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {InvoiceComponent} from "../app/invoice/invoice.component";
import {ProductComponent} from "../app/product/product.component";
import {ProductCreateComponent} from "../app/product/product-create/product-create.component";
import {ProductReportComponent} from "../app/product/product-report/product-report.component";
import {InvoiceCreateComponent} from "../app/invoice/invoice-create/invoice-create.component";
import {InvoiceReportComponent} from "../app/invoice/invoice-report/invoice-report.component";

const routes: Routes = [
  {
    path: 'invoices',
    component: InvoiceComponent,
    children: [
      {path: '', redirectTo:'view', pathMatch: 'full'},
      {path: 'create', component: InvoiceCreateComponent},
      {path: 'view', component: InvoiceReportComponent}
    ]
  },
  {
    path: 'product',
    component: ProductComponent,
    children: [
      {path:'', redirectTo: 'view', pathMatch: 'full'},
      {path: 'create', component: ProductCreateComponent},
      {path: 'view', component: ProductReportComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {

}
