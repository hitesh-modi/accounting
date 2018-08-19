import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent implements OnInit {

  constructor() { }

  @Input("successMessage") successMessage: string;
  @Input("closable") closable: boolean;

  closeSuccessMessage: boolean = true;

  ngOnInit() {
  }

}
