import { Component } from '@angular/core';
import { CommandeService } from 'src/app/services/commande.service';
import { DemmandeVendeurService } from 'src/app/services/demmande-vendeur.service';
import { ProduitService } from 'src/app/services/produit-service.service';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-statistique',
  templateUrl: './statistique.component.html',
  styleUrls: ['./statistique.component.css']
})
export class StatistiqueComponent {
  numberOfCommande!:number;
  numberOfProduit!:number;
  numberOfUser!:number;
  numberOfDemmande!:number;
  constructor(private userService:UserService,private produitService:ProduitService,private demmandeService:DemmandeVendeurService,private commandeService:CommandeService) {
  }

  ngOnInit(){
    this.numberCommande();
    this.numberUsers();
    this.numberProduit();
    this.numberDemmande()
  }
  numberProduit(){
      this.produitService.count().subscribe((res)=>{
        this.numberOfProduit = res
      })
      
  }

  numberUsers(){
    this.userService.count().subscribe((res)=>{
      this.numberOfUser = res
    })
  }

  numberCommande(){
    this.commandeService.count().subscribe((res)=>{
      this.numberOfCommande = res
    })
  }

  numberDemmande(){
    this.demmandeService.count().subscribe((res)=>{
      this.numberOfDemmande = res
    })
  }
}
