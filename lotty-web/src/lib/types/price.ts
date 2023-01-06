
export enum Prices {
  First,
  Second,
  Third
}

export class Price {
  price: Prices
  description: String

  constructor(price: Prices, description: String) {
    this.price = price;
    this.description = description;
  }
}