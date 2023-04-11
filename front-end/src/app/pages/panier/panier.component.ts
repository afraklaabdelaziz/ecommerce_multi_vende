import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Commande } from 'src/app/models/commande';
import { CommandeService } from 'src/app/services/commande.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit{
  constructor(private commandeService:CommandeService) {
    this.commande = new Commande()
  }
  ngOnInit(): void {
    let produits = localStorage.getItem('cart');
     this.produits = JSON.parse(produits!)
    for (let i = 0; i < this.produits.length; i++) {
      this.prixTotal+= this.produits[i].prix * this.produits[i].quantity
    }
  }

  produits:any = []

  commande:Commande

  prixTotal:number = 0

  quantity:number = 3
  addition() {
    this.quantity++
  }

  substraction(){
    if (this.quantity == 1){
      this.quantity = 1
    }else {
    this.quantity--
  }}

  delete(produit:any) {
    this.produits
  }

  commander() {
    this.commande.prixTotal = this.prixTotal
    this.commande.commandeItemsList = this.produits
    this.commande.statusCommande = 'EN_COURS'
    this.commandeService.addCommande(this.commande).subscribe((res)=>{
      if (res.status == 'success'){
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: res.message,
          showConfirmButton: false,
          timer: 1500
        })
        localStorage.removeItem("cart");
      }else{
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: res.message,
          showConfirmButton: true,
          timer: 2000
        })
      }
  })
  }
}
