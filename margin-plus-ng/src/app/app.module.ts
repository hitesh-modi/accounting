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

export function init_app(appInitService: AppInitializerService) {
 return () => appInitService.initaializeApp();
}

@NgModule({
  declarations: [
    AppComponent,
    InvoiceComponent,
    HomeComponent
  ],
  imports: [
    AppRoutingModule,
    UiModule,
    BrowserModule,
    ClarityModule,
    ClrFormsNextModule,
    FormsModule
  ],
  providers: [AppInitializerService, AppModuleConfig, InvoiceService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
