import {Component, OnInit} from '@angular/core';
import {GlobalDataService} from "../../../user/service/global.data.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loginSuccess: boolean = false;

  showLoginModal: boolean;

  dashActive: boolean = true;
  invoiceActive: boolean = false;
  productActive: boolean = false;
  reportActive: boolean = false;

  constructor(public globalDataService: GlobalDataService) { }

  updateDirectives(loginStatus: boolean) {
    console.log('Login Status', loginStatus, this.globalDataService.userinfo);
    if(loginStatus == true) {
      this.showLoginModal = false;
    }
    this.loginSuccess = loginStatus;
  }

  toggleNavigation(active: string) {
    switch(active) {
      case 'dashboard':
        this.dashActive = true;
        this.productActive = false;
        this.invoiceActive = false;
        this.reportActive = false;
        break;
      case 'invoice':
        this.dashActive = false;
        this.productActive = false;
        this.invoiceActive = true;
        this.reportActive = false;
        break;
      case 'product':
        this.dashActive = false;
        this.productActive = true;
        this.invoiceActive = false;
        this.reportActive = false;
        break;
      case 'report':
        this.dashActive = false;
        this.productActive = false;
        this.invoiceActive = false;
        this.reportActive = true;
        break;
    }
  }

  ngOnInit() {
  }

}
