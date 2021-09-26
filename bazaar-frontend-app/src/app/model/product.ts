import {Category} from "./category";
import {Provider} from "./provider";

export class Product {

  constructor(public id: number,
              public title: string,
              public cost: number,
              public category: Category,
              public provider: Provider,
              public pictures: number[]) {
  }
}
