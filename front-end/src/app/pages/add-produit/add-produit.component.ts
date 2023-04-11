import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Produit } from 'src/app/models/produit';
import { CategotyServiceService } from 'src/app/services/categoty-service.service';
import { ProduitService } from 'src/app/services/produit-service.service';
import { UserService } from 'src/app/services/user-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-produit',
  templateUrl: './add-produit.component.html',
  styleUrls: ['./add-produit.component.css']
})
export class AddProduitComponent implements OnInit{
  produit:Produit;
  token: any;
  user: any;
  email!: string;

  categories:Category[] = []
  constructor(private produitService:ProduitService,private categoryService:CategotyServiceService,public router:Router,private userService:UserService,private sanitizare:DomSanitizer) {
    this.produit = new Produit();
  }

  ngOnInit(): void {
        this.categoryService.getAllGategories().subscribe((res)=>{
          this.categories = res.data
          console.log(this.categories[0].nom)
        })
    }

  addHotel(form: NgForm) {
    this.token = this.userService.getToken()
    this.user = this.userService.getUser(this.token);
    this.email = this.user.sub;
    //const produitForm = this.prepareFormData(this.produit)
    this.produitService.addProduit(this.produit).subscribe((res)=>{
      if (res.status === 'success'){
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

  refresh(): void {
    window.location.reload();
  }

  // imageSelected(event:any) {
  //   if (event.target.files){
  //     const file:File = event.target.files[0];
  //
  //     const image:Image = new Image (file,this.sanitizare.bypassSecurityTrustUrl(
  //       window.URL.createObjectURL(file)
  //     ))
  //     this.hotel.image.push(image)
  //   }
  // }
  //
  // prepareFormData(hotel: Hotel) : FormData{
  //   const formData = new FormData();
  //   formData.append(
  //     'hotel',
  //     new Blob([JSON.stringify(hotel)],{type: 'application/json'})
  //   );
  //   for (let i = 0; i < hotel.image.length; i++) {
  //     formData.append(
  //       'imageFile',
  //       hotel.image[i].file,
  //       hotel.image[i].file.name
  //     )
  //   }
  //   return formData
  //}
}
