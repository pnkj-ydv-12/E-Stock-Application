import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Company } from '../model/company';
import { StockPrice } from '../model/stockPrice';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit{
  companyForm!: FormGroup;
  selectedCompany: Company | null = null;
  sP !:StockPrice;
  constructor(
    private authService: AuthService,
    private http: HttpClient,
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit() {
    this.companyForm = this.formBuilder.group({
      stockPrice: ['', Validators.required],
      companyCode: ['',Validators.required],
      // Add other form controls here
    });
    console.log('Inside ngOnInit');
    
  }

  submitForm() {
    console.log('Company Code:', this.companyForm.value.companyCode);
    if (this.companyForm.invalid) {
      return;
    }
    const companyCode = this.companyForm.value.companyCode; 
    console.log(companyCode);// Assuming you have a form control for companyCode
  const url = `http://localhost:8081/v1.0/market/stock/add/${companyCode}`;
    //const url = `http://localhost:8081/v1.0/market/stock/add/${this.selectedCompany?.companyCode}`;
    const token = sessionStorage.getItem('token');
    const body = {
      price: this.companyForm.value.stockPrice,
      
      // Add other properties as needed
    };
    console.log(body);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      })
    };

    this.http.post(url, body, httpOptions).subscribe(
      () => {
        console.log('Stock added successfully');
        this.router.navigate(['/company']);
      },
      (error) => {
        console.error(error);
      }
    );

    Swal.fire({
      icon: 'success',
      title: 'Success',
      text: 'Stock added successfully!',
    });
  }

}
