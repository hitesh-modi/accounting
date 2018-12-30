import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {MatSnackBar} from "@angular/material";
import {Injectable} from "@angular/core";

@Injectable()
export class AppRequestInterceptor implements HttpInterceptor {

  constructor(
    public snackBar: MatSnackBar) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).do((event: HttpEvent<any>) => {}, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        this.snackBar.open("Error Occurred, please try after sometime.", "",{duration: 2000});
      }
    });
  }

}
