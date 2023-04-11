import { CommandeItem } from "./commande-item"

export class Commande {
  id?:number
  prixTotal:number
  commandeItemsList:CommandeItem[]
  date:Date
  statusCommande:String


  constructor() {
    this.prixTotal = 0;
    this.commandeItemsList = [];
    this.date = new Date();
    this.statusCommande = "";
  }
}
