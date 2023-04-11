import { User } from "./user";

export class DemmandeVendeur {
  id?:number;
  demmande:String
  userApp:User

  constructor() {
    this.demmande = "";
    this.userApp = new User()
  }
}
