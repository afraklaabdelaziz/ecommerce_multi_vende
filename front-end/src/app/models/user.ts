import { Adresse } from "./adresse";
import { Role } from "./role";

export class User {
  id?: number;
  nom:string;
  prenom:string;
  email: string;
  telephone:string;
  motDePasse:string;
  role:Role;
  adresse:Adresse;


  constructor() {
    this.nom = "";
    this.prenom = "";
    this.email = "";
    this.telephone = "";
    this.motDePasse = "";
    this.adresse = new Adresse();
    this.role = new Role();
  }

}
