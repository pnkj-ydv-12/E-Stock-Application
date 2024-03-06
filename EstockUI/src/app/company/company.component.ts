import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Company } from '../model/company';
import { AuthService } from '../service/auth.service';
import { EstockService } from '../service/EStock.service';
import Swal from 'sweetalert2';
import { MatDialog } from '@angular/material/dialog';
import { UpdateFormComponent } from '../update-form/update-form.component';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent {
  

  companyForm!: FormGroup;
  companies: Company[] = [];
  searchText: string = '';
  isUpdate = false;
  selectedCompany: Company | null = null;
 
  constructor(private authService: AuthService, private http: HttpClient, private router: Router,private formBuilder: FormBuilder, private dialog: MatDialog, private Es:EstockService) { }
 
  ngOnInit() {
    this.companyForm = this.formBuilder.group({
      companyCode: ['', Validators.required],
      companyName: ['', Validators.required],
      companyCEO: ['', Validators.required],
      companyTurnover: ['', Validators.required],
      companyWebsite: ['', Validators.required],
      stockExchange: ['', Validators.required],
      
      
    });
    this.getAllCompanies();
    
   
   
  }
 
  updateForm() {
    console.log("inside");
    if (this.companyForm.invalid) {
      console.log("invalid");
      return;
    }
  
    const companyCodeToUpdate = this.companyForm.value.companyCode; // Assuming you have a hidden input for companyCode
  
    const updateBody = {
      companyName: this.companyForm.value.companyName,
      companyCEO: this.companyForm.value.companyCEO,
      companyTurnover: this.companyForm.value.companyTurnover,
      companyWebsite: this.companyForm.value.companyWebsite,
      stockExchange: this.companyForm.value.stockExchange,
    };
    console.log(updateBody);
    this.Es.updateCompany(companyCodeToUpdate, updateBody).subscribe(
      () => {
        console.log('Company updated successfully');
        // Handle success response here
        this.getAllCompanies();
      },
      (error) => {
        console.error(error);
        // Handle error response here
      }
    );
  }
  
  
  submitForm() {
    if (this.companyForm.invalid) {
      return;
    
    }
    console.log("inside")
    const url = 'http://localhost:8081/v1.0/market/company/register';
    const token = sessionStorage.getItem('token');
    const body = {
      // companyCode: this.companyForm.value.companyCode,
      companyName: this.companyForm.value.companyName,
      companyCEO: this.companyForm.value.companyCEO,
      companyTurnover: this.companyForm.value.companyTurnover,
      companyWebsite: this.companyForm.value.companyWebsite,
      stockExchange: this.companyForm.value.stockExchange,
      //stockPrice: this.companyForm.value.stockPrice,
     // latestStockPrice: this.companyForm.value.latestStockPrice,
      
    };
    console.log(this.companyForm) 
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      })
    };
 
    console.log('Company Name:', this.companyForm.value.companyName);
    
    this.http.post(url, body, httpOptions).subscribe(
      () => {
        console.log('Company added successfully');
        // Handle success response here
        this.getAllCompanies();
        
      },
      (error) => {
        console.error(error);
        // Handle error response here
      }
    );
    Swal.fire({
      icon: 'success',
      title: 'Success',
      text: 'Company added successfully!',
    });
   
  }
getAllCompanies() {
  const url = 'http://localhost:8081/v1.0/market/company/getAll';
  const token = sessionStorage.getItem('token');

  console.log("Token -> ", token);
 
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer ' + token
    })
  };
  console.log("httpOptions -> ", httpOptions);
  this.http.get<Company[]>(url, httpOptions).subscribe(movies => {
    this.companies = movies.map(movie => ({
      ...movie,
      editMode: false
    }));
    const latestStockPrices = this.companies.map(company => company.latestStockPrice);
        console.log('Latest Stock Prices:', latestStockPrices);
  });
}
deleteCompany(companyCode: number): void {
  const url = `http://localhost:8081/v1.0/market/company/delete/${companyCode}`;
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem('token')
    }),
    responseType: 'text' as 'json' // here
  };
  this.http.delete(url, httpOptions).subscribe(
    () => {
      console.log("Reloading admin dashboard after delete!!");
      this.ngOnInit();
    },
    (error) => {
      console.error(error);
    }
  );
}
goToCompanyInfo(companyCode: number): void {
  this.router.navigate(['/company-info', companyCode]);
}
}
