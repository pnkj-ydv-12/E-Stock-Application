import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginResponse } from '../model/loginresponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrlEstockMarketApplication = 'http://localhost:8081';
  private apiUrlSpringBootjwt = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<LoginResponse> {
    const url = `${this.apiUrlSpringBootjwt}/auth/v1.0/login`;
    console.log(this.login);
    return this.http.post<LoginResponse>(url, { username, password });
  }

  

  logout(): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      })
    };

    const url = `${this.apiUrlSpringBootjwt}/logout`;
    sessionStorage.clear();
    return this.http.post(url, {}, { responseType: 'text', headers: httpOptions.headers });
  }

  // Additional methods can be added based on your requirements

}
