import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Produit } from 'src/app/models/produit';
import { ProduitService } from 'src/app/services/produit-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-produit-vendeur',
  templateUrl: './list-produit-vendeur.component.html',
  styleUrls: ['./list-produit-vendeur.component.css']
})
export class ListProduitVendeurComponent {
  produits: any;
  produitFound!: Produit;
  idProduit!: number;

  constructor(private produitService: ProduitService, private router: Router,private sanitizer:DomSanitizer) {
    this.produitFound = new Produit()
  }

  private _color = "light";

  showModal = false;
  showModalAdd = false;
  totalPage:number = 0
  page:number = 0

  toggleModal() {
    this.showModal = !this.showModal;
  }

  toggleModalAdd() {
    this.showModalAdd = !this.showModalAdd;
  }

  ngOnInit(): void {
    this.produitService.getAllProduits(0).subscribe((res) => {
      this.getAllProduit(this.page)
    })
  }

  getAllProduit(page:number){
    this.produitService.getAllProduitOfVendor(page).subscribe((res)=>{
      this.produits = res.content
      this.totalPage = res.totalPages
    })
  }

  delete(id: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.produitService.deleteProduit(id).subscribe((res: any) => {
          if (res.status == 'success') {
            Swal.fire(
              'Deleted!',
              res.message,
              'success'
            )
            this.refresh()
          }
        })

      }
    })
  }

  refresh(): void {
    window.location.reload();
  }

  update(id: number) {
    this.produitService.getOneProduit(id).subscribe((res) => {
      this.produitFound = res.data;
      this.idProduit = id;
    })
  }

  updateHotel(form: NgForm) {
    this.produitService.updateProduit(this.produitFound).subscribe((res) => {
      if (res.status === 'success') {
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: res.message,
          showConfirmButton: false,
          timer: 1500
        })
      }
      this.refresh()
    })
  }
}
