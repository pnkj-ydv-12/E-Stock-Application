import { StockExchange } from "./stockExchange";
import { StockPrice } from "./stockPrice";
export class Company {
    public companyCode: number;
    public companyName: string;
    public companyCEO: string;
    public companyTurnover: number;
    public companyWebsite: string;
    public stockExchange: StockExchange;
    public stockPrice: StockPrice[];
    public latestStockPrice: StockPrice;
      
    constructor(
      companyCode: number,
      companyName: string,
      companyCEO: string,
      companyTurnover: number,
      companyWebsite: string,
      stockExchange: StockExchange,
      stockPrice: StockPrice[],
      latestStockPrice: StockPrice,

    ) {
      this.companyCode = companyCode;
      this.companyName = companyName;
      this.companyCEO = companyCEO;
      this.companyTurnover = companyTurnover;
      this.companyWebsite = companyWebsite;
      this.stockExchange = stockExchange;
      this.stockPrice = stockPrice;
      this.latestStockPrice = latestStockPrice;
   
    }
  }
  
  
  
  