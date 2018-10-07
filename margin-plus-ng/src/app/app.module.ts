import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppComponent } from './app.component';
import {UiModule} from "./ui/ui.module";
import { InvoiceComponent } from './app/invoice/invoice.component';
import {AppRoutingModule} from "./app-routing/app-routing.module";
import { HomeComponent } from './app/home/home.component';
import {UserService} from "./user/service/user.service";
import {GlobalDataService} from "./user/service/global.data.service";
import {AppInitializerService} from "./app/services/app.initializer.service";
import {AppModuleConfig} from "./app.module.config";
import {InvoiceService} from "./app/invoice/service/invoice.service";
import {ClarityModule, ClrFormsNextModule} from "@clr/angular";
import {FormsModule} from "@angular/forms";
import {DatePipe} from "@angular/common";
import { ProductComponent } from './app/product/product.component';
import { ProductCreateComponent } from './app/product/product-create/product-create.component';
import { ProductReportComponent } from './app/product/product-report/product-report.component';
import { AccountingCodesComponent } from './app/product/accounting-codes/accounting-codes.component';
import { ErrorComponent } from './app/shared/error/error.component';
import { SuccessComponent } from './app/shared/success/success.component';
import { InvoiceReportComponent } from './app/invoice/invoice-report/invoice-report.component';
import { InvoiceCreateComponent } from './app/invoice/invoice-create/invoice-create.component';
import { CustomerComponent } from './app/invoice/customer/customer.component';
import { ConsigneeComponent } from './app/invoice/consignee/consignee.component';
import { InvoiceWizardComponent } from './app/invoice/invoice-wizard/invoice-wizard.component';
import {MatDatepickerModule, MatInputModule, MatNativeDateModule, MatSnackBarModule} from "@angular/material";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AppRequestInterceptor} from "./interceptor/http.interceptor";

export function init_app(appInitService: AppInitializerService) {
  console.log("Initializing Data");
 return () => appInitService.initaializeApp();
}

@NgModule({
  declarations: [
    AppComponent,
    InvoiceComponent,
    HomeComponent,
    ProductComponent,
    ProductCreateComponent,
    ProductReportComponent,
    AccountingCodesComponent,
    ErrorComponent,
    SuccessComponent,
    InvoiceReportComponent,
    InvoiceCreateComponent,
    CustomerComponent,
    ConsigneeComponent,
    InvoiceWizardComponent
  ],
  imports: [
    AppRoutingModule,
    UiModule,
    BrowserModule,
    ClarityModule,
    ClrFormsNextModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSnackBarModule,
    MatInputModule
  ],
  providers: [AppInitializerService,
    AppModuleConfig,
    InvoiceService,
    DatePipe,
    { provide: APP_INITIALIZER, useFactory: init_app, deps: [AppInitializerService, UserService, GlobalDataService], multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AppRequestInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
