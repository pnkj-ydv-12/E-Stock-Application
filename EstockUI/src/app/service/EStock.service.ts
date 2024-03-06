import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable } from 'rxjs';
import { Company } from '../model/company';
import { StockPrice } from '../model/stockPrice';

@Injectable({
  providedIn: 'root'
})
export class EstockService {

  private apiUrlEStockMarketApplication = 'http://localhost:8081';
  private apiUrlEStockMarketControllers = 'http://localhost:8080'; // Adjust the port as needed
  private apiUrl = 'http://localhost:8081/v1.0/market/company';
  private apiInfo = 'http://localhost:8081/v1.0/market/company/info'
  private apiStockTransactions = 'http://localhost:8081/v1.0/market/stock/getAllStock';
  constructor(private http: HttpClient) { }


  updateCompany(companyCode: number, body: any): Observable<any> {
    const url = `${this.apiUrl}/put/${companyCode}`;
    const token = sessionStorage.getItem('token');

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      })
    };

    return this.http.put(url, body, httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any): Observable<never> {
    console.error('An error occurred:', error);
    throw error;
  }
  getCompanyDetailsByCode(companyCode: number, token: string): Observable<Company> {
    const url = `${this.apiInfo}/${companyCode}`;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    };
    return this.http.get<Company>(url, httpOptions);
  }
  getStockTransactionsByCompanyCode(companyCode: number, token: string): Observable<StockPrice[]> {
    const url = `${this.apiStockTransactions}/${companyCode}`;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    };
    return this.http.get<StockPrice[]>(url, httpOptions);
  }


}

