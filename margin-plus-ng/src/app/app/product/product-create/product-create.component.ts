import { Component, OnInit } from '@angular/core';
import {ProductModel} from "../model/ProductModel";
import {isNullOrUndefined} from "util";
import {ProductService} from "../service/product.service";
import {GlobalDataService} from "../../../user/service/global.data.service";

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {

  product: ProductModel = new ProductModel();

  showAccountingModal: boolean;

  success: boolean = true;

  showSuccessMessage: boolean = false;

  constructor(private productService: ProductService, private gds: GlobalDataService) {
    this.product.type = 'Good';
  }

  ngOnInit() {
  }

  checkProductForm(): boolean {
    if((!isNullOrUndefined(this.product.type) && this.product.type.length > 0) &&
       (!isNullOrUndefined(this.product.name) && this.product.name.length > 0) &&
       (!isNullOrUndefined(this.product.company) && this.product.company.length > 0) &&
       (!isNullOrUndefined(this.product.hsnCode) && this.product.hsnCode.length > 0)) {
      return false;
    } else {
      return true;
    }
  }

  submitProduct() {
    if(this.product.type == 'Good') {
      this.product.good == true;
    }
    if(this.product.type == 'Service') {
      this.product.good = false;
    }
    this.productService.submitProduct(this.product, this.gds.userinfo.userid).subscribe(
      onloadeddata => {
        this.showSuccessMessage = true;
      },
      e => {
        this.success = false;
      }
    );
  }

}
