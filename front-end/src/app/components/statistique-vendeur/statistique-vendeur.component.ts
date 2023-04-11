import { Component } from '@angular/core';
import { CommandeService } from 'src/app/services/commande.service';
import { ProduitService } from 'src/app/services/produit-service.service';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-statistique-vendeur',
  templateUrl: './statistique-vendeur.component.html',
  styleUrls: ['./statistique-vendeur.component.css']
})
export class StatistiqueVendeurComponent {
  numberOfCommande!:number;
  numberOfProduit!:number;
  numberOfUser!:number;
  constructor(private userService:UserService,private produitService:ProduitService,private commandeService:CommandeService) {
  }

  ngOnInit(){
    this.numberCommande();
    this.numberUsers();
    this.numberProduit();
  }
  numberProduit(){
    this.produitService.countByVendeur().subscribe((res)=>{
      this.numberOfProduit = res
    })

  }

  numberUsers(){
    this.numberOfUser = 5
  }

  numberCommande(){
    this.commandeService.countVendeur().subscribe((res)=>{
      this.numberOfCommande = res
    })
  }

  numberReservations(){
   
  }
}
