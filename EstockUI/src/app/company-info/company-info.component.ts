import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Company } from '../model/company';
import { StockPrice } from '../model/stockPrice';
import { AuthService } from '../service/auth.service';
import { EstockService } from '../service/EStock.service';
import { ChartConfiguration, ChartOptions, ChartDataset, Chart } from 'chart.js';

@Component({
  selector: 'app-company-info',
  templateUrl: './company-info.component.html',
  styleUrls: ['./company-info.component.css']

})
export class CompanyInfoComponent implements OnInit {
  companyCode!: number;
  companyDetails!: Company;
  stockTransactions: StockPrice[] = [];
  filteredTransactions: StockPrice[] = [];
  priceFilter: number | null = null;
  isFilterApplied: boolean = false;
  startDate: string | null = null;
  endDate: string | null = null;
  maxStockPrice: number | null = null;
  minStockPrice: number | null = null;
  avgStockPrice: number | null = null;
  


  constructor(private route: ActivatedRoute, private http: HttpClient, private es: EstockService, private authService: AuthService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.companyCode = +params['companyCode'];
      this.getCompanyDetails();
      this.getStockTransactions();
    });
  }

  getCompanyDetails(): void {
    console.log("insdie getcompanyDetails");
    
    const token = sessionStorage.getItem('token');
    if (!token) {
      // Handle the case when there is no token (e.g., redirect to login)
      console.error('No authorization token available.');
      return;
    }
    this.es.getCompanyDetailsByCode(this.companyCode, token).subscribe(
      (data: Company) => {
        console.log(data);
        this.companyDetails = data;
      },
      (error) => {
        console.error(error);
        // Handle error response here
      }
    );
  }
  getStockTransactions(): void {
    const token = sessionStorage.getItem('token');
    if (!token) {
      console.error('No authorization token available.');
      return;
    }
    this.es.getStockTransactionsByCompanyCode(this.companyCode, token).subscribe(
      (data: StockPrice[] | any) => {
        console.log('Data received:', data);
  
        if (Array.isArray(data)) {
          this.stockTransactions = data;
          console.log(this.stockTransactions);
          this.calculateMaxPrice();
          this.calculateMinPrice();
          this.calculateAveragePrice();
        } else {
          console.error('Data is not an array:', data);
        }
      },
      (error) => {
        console.error(error);
      }
    );
  }
  
  filterTableByPrice(): void {
    if (this.priceFilter !== null || this.priceFilter !== undefined) {
      // Apply the filter using Angular's built-in filter pipe
      this.filteredTransactions = this.stockTransactions.filter(transaction => transaction.price === this.priceFilter);
      this.isFilterApplied = true; // Set the flag
    } else {
      // If the filter is null or undefined, show all transactions
      this.filteredTransactions = this.stockTransactions;
      this.isFilterApplied = false; // Reset the flag
    }
  }
  clearFilter(): void {
    // Clear the filter and reset the filtered transactions
    this.priceFilter = null;
    this.filteredTransactions = this.stockTransactions;
    this.isFilterApplied = false;
    this.startDate = null;
    this.endDate = null;
  }
  filterTableByDateRange(): void {
    // Check if startDate and endDate are not null
    if (this.startDate !== null && this.endDate !== null) {
      // Convert startDate and endDate to Date objects
      const startDateObject = new Date(this.startDate as string);
      const endDateObject = new Date(this.endDate as string);
  
      // Check if the date is valid
      if (!isNaN(startDateObject.getTime()) && !isNaN(endDateObject.getTime())) {
        // Apply the filter using Angular's built-in filter pipe
        this.filteredTransactions = this.stockTransactions.filter(transaction => {
          const transactionDate = new Date(transaction.timeStamp);
  
          // Check if the transaction date is within the selected range
          return transactionDate >= startDateObject && transactionDate <= endDateObject;
        });
  
        this.isFilterApplied = true; // Set the flag
      } else {
        console.error('Invalid date range');
      }
    } else {
      console.error('Start date or end date is null');
    }
  }
  calculateMaxPrice(): void {
    if (this.stockTransactions.length > 0) {
      this.maxStockPrice = Math.max(...this.stockTransactions.map(transaction => transaction.price));
    } else {
      this.maxStockPrice = null;
    }
  }

  calculateMinPrice(): void {
    if (this.stockTransactions.length > 0) {
      this.minStockPrice = Math.min(...this.stockTransactions.map(transaction => transaction.price));
    } else {
      this.minStockPrice = null;
    }
  }

  calculateAveragePrice(): void {
    if (this.stockTransactions.length > 0) {
      const total = this.stockTransactions.reduce((sum, transaction) => sum + transaction.price, 0);
      this.avgStockPrice = total / this.stockTransactions.length;
    } else {
      this.avgStockPrice = null;
    }
  }
  
  
  
  
}
