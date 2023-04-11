import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Produit } from 'src/app/models/produit';
import { ProduitService } from 'src/app/services/produit-service.service';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css']
})
export class ProduitsComponent implements OnInit{
  produits!:Produit[]
  cart:any = []
  produitJson!:any
  totalPage:number = 0
  page:number = 0

  constructor(private produitService:ProduitService) {
  }

  ngOnInit(): void {
         this.getAllProduit(this.page)
    }

    getAllProduit(page:number){
      this.produitService.getAllProduits(page).subscribe((res)=>{
        this.produits = res.content
        this.totalPage = res.totalPages
      })
    }

  addToCart(produit:Produit,quantity:number) {
          this.produitJson ={
            "id":produit.id,
            "nom":produit.nom,
            "prix":produit.prix,
            "quantity":quantity
          }
          this.cart.push(this.produitJson)

    localStorage.setItem("cart",JSON.stringify(this.cart))
  }

}
