import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { DemmandeVendeur } from 'src/app/models/demmande-vendeur';
import { DemmandeVendeurService } from 'src/app/services/demmande-vendeur.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-demmande-vendeur',
  templateUrl: './add-demmande-vendeur.component.html',
  styleUrls: ['./add-demmande-vendeur.component.css']
})
export class AddDemmandeVendeurComponent {
  demmande:DemmandeVendeur;
  constructor(private demmandeService:DemmandeVendeurService,private sanitizare:DomSanitizer) {
    this.demmande = new DemmandeVendeur();
  }

  addDemmande(form: NgForm) {
    this.demmandeService.addDemmande(this.demmande).subscribe((res)=>{
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
}
