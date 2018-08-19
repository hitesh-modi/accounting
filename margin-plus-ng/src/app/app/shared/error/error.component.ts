import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  @Input("errorMessage") errorMessage: string;
  @Input("closable") closable: boolean;

  @Output("refresh") refresh: EventEmitter<boolean> = new EventEmitter(false);
  @Input("closeErrorMessage") closeErrorMessage: boolean = true;

  constructor() { }

  ngOnInit() {
  }

  refreshView() {
    this.closeErrorMessage = false;
    return this.refresh.emit(this.closeErrorMessage);
  }

}
