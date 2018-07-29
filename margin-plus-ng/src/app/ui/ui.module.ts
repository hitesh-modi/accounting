import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from './layout/layout.component';
import { HeaderComponent } from './layout/header/header.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { MainComponent } from './layout/main/main.component';
import {UserModule} from "../user/user.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AppRoutingModule} from "../app-routing/app-routing.module";
import {BrowserModule} from "@angular/platform-browser";
import {ClarityModule} from "@clr/angular";

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ClarityModule,
    CommonModule,
    UserModule
  ],
  exports: [
    LayoutComponent,
    HeaderComponent
  ],
  declarations: [LayoutComponent, HeaderComponent, SidebarComponent, MainComponent]
})
export class UiModule { }
