import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './login/login.component';
import {FormsModule} from "@angular/forms";
import {ClarityModule, ClrFormsNextModule} from "@clr/angular";

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    ClarityModule,
    ClrFormsNextModule,
    FormsModule
  ],
  exports: [
    LoginComponent
  ],
  declarations: [LoginComponent]
})
export class UserModule { }
