import { Component, OnInit } from '@angular/core';
import {ProductModel} from "./model/ProductModel";
import {ProductService} from "./service/product.service";
import {GlobalDataService} from "../../user/service/global.data.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Array<ProductModel>;

  constructor(private productService: ProductService, private globalDataService: GlobalDataService) { }

  ngOnInit() {

  }

}
