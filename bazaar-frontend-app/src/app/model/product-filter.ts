export class ProductFilterDto {

  constructor(public title:string,
              public minCost:number,
              public maxCost:number) {
  }
}
