export class StockPrice {
    public id: number;
    public price: number;
    public timeStamp: Date;
  
    constructor(id: number, price: number, timeStamp: Date) {
      this.id = id;
      this.price = price;
      this.timeStamp = timeStamp;
    }
  }