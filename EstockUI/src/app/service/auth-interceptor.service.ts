// import {
//     HttpErrorResponse,
//     HttpEvent,
//     HttpHandler,
//     HttpInterceptor,
//     HttpRequest,
//   } from '@angular/common/http';
//   import { Router } from '@angular/router';
//   import { catchError } from 'rxjs/operators';
//   import { Injectable } from '@angular/core';
//   import { Observable, throwError } from 'rxjs';
   
//   @Injectable()
//   export class AuthInterceptor implements HttpInterceptor {
//   constructor(private router: Router) {}
   
//   intercept(
//     req: HttpRequest<any>,
//     next: HttpHandler
//   ): Observable<HttpEvent<any>> {
//     if (req.headers.get('NoAuth') === 'True') {
//       return next.handle(req.clone());
//     }
   
//     const token = sessionStorage.getItem('token');
//     if(token){
//       req = this.addToken(req, token);
//     }
   
//     return next.handle(req).pipe(
//         catchError(
//             (err:HttpErrorResponse) => {
//                 console.log(err.status);
//                 if(err.status === 401) {
//                     this.router.navigate(['/login']);
//                 } else if(err.status === 403) {
//                     this.router.navigate(['/forbidden']);
//                 }
//                 return throwError(err.message || "An error occurred");
//             }
//         )
//     );
//   }
   
//   private addToken(request: HttpRequest<any>, token: string) {
//     return request.clone({
//         setHeaders: {
//             Authorization : `Bearer ${token}`
//         }
//     });
//   }
//   }