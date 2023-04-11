import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { DemmandeVendeur } from 'src/app/models/demmande-vendeur';
import { DemmandeVendeurService } from 'src/app/services/demmande-vendeur.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-demmande-vendeur',
  templateUrl: './list-demmande-vendeur.component.html',
  styleUrls: ['./list-demmande-vendeur.component.css']
})
export class ListDemmandeVendeurComponent {
  constructor(private demmandeService: DemmandeVendeurService,private sanitizer:DomSanitizer) {
  }

  private _color = "light";

  demmandes:DemmandeVendeur[] = []


  ngOnInit(): void {
    this.demmandeService.getAllDemmande().subscribe((res) => {
      this.demmandes = res.data;
    })
  }


  response(response:boolean,id: any) {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'bg-green-500 mx-3 text-white font-bold py-2 px-4 rounded',
        cancelButton: 'bg-red-500 text-white font-bold py-2 px-4 rounded'
      },
      buttonsStyling: false
    })

    if (response){
      swalWithBootstrapButtons.fire({
        title: 'Are you sure?',
        text: "accept demmande",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Oui, Accepter',
        cancelButtonText: 'Non, annuler!',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
          this.demmandeService.responceDemmande(response,id).subscribe((res) => {
            if (res.status == 'success') {
              swalWithBootstrapButtons.fire(
                'Accept',
                res.message,
                'success'
              )
            }
          })

        } else if (
          /* Read more about handling dismissals below */
          result.dismiss === Swal.DismissReason.cancel
        ) {
          swalWithBootstrapButtons.fire(
            'Cancelled',
            'Your imaginary file is safe :)',
            'error'
          )
        }
      })
    }else {
      swalWithBootstrapButtons.fire({
        title: 'Are you sure?',
        text: "refuser demmande",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Oui , Refuser',
        cancelButtonText: 'Non, annuler!',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
          this.demmandeService.responceDemmande(response,id).subscribe((res) => {
            if (res.status == 'success') {
              swalWithBootstrapButtons.fire(
                'Refuse',
                res.message,
                'warning'
              )
            }
          })

        } else if (
          /* Read more about handling dismissals below */
          result.dismiss === Swal.DismissReason.cancel
        ) {
          swalWithBootstrapButtons.fire(
            'Cancelled',
            'Your imaginary file is safe :)',
            'error'
          )
        }
      })
    }
  }



}
