import { Component } from '@angular/core';

@Component({
  selector: 'app-panier-drop-down',
  templateUrl: './panier-drop-down.component.html',
  styleUrls: ['./panier-drop-down.component.css']
})
export class PanierDropDownComponent {
  ngOnInit(): void {
    let produits = localStorage.getItem('cart');
    this.produits = JSON.parse(produits!)
    if (this.produits !=null){
      this.length = this.produits.length
    }
    for (let i = 0; i < this.produits.length; i++) {
      this.prixTotal+= this.produits[i].prix * this.produits[i].quantity
    }

  }
  produits:any =[]
  prixTotal:number = 0
  length:number = 0
}
