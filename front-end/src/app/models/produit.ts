import { Category } from "./category";

export class Produit {
  id?:number
  nom:String
  prix:number
  quantity:number;

  category: Category;


  constructor() {
    this.nom = ""
    this.prix = 0.0;
    this.quantity = 0;
    this.category = new Category();
  }
}
