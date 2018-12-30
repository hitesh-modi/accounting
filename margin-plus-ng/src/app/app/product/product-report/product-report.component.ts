import { Component, OnInit } from '@angular/core';
import {ProductService} from "../service/product.service";
import {GlobalDataService} from "../../../user/service/global.data.service";
import {ProductModel} from "../model/ProductModel";

@Component({
  selector: 'app-product-report',
  templateUrl: './product-report.component.html',
  styleUrls: ['./product-report.component.css']
})
export class ProductReportComponent implements OnInit {

  products: Array<ProductModel>;

  constructor(private productService: ProductService, private globalDataService: GlobalDataService) { }

  ngOnInit() {
    this.productService.getProducts(this.globalDataService.userinfo.userid).subscribe(onloadeddata => {
      this.products = onloadeddata;
    });
  }

}
