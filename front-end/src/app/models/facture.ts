import { Commande } from "./commande"
import { User } from "./user"

export class Facture {
  id?:number
  commande:Commande
  client:User


  constructor() {
    this.commande = new Commande();
    this.client = new User();
  }
}
