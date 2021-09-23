import {Category} from "./category";
import {Provider} from "./provider";

export class Product {

  constructor(public id: number,
              public title: string,
              public cost: string,
              public category: Category,
              public provider: Provider,
              public pictures: number[]) {
  }
}
