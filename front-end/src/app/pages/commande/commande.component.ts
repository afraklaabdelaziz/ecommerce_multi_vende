import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Commande } from 'src/app/models/commande';
import { CommandeService } from 'src/app/services/commande.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-commande',
  templateUrl: './commande.component.html',
  styleUrls: ['./commande.component.css']
})
export class CommandeComponent implements OnInit{
  commandes:any = []
  status:any = "EnCours"
  constructor(private commandeService:CommandeService,private router:Router) {
  }

  ngOnInit(): void {
    this.commandeService.getAllCommmande().subscribe((res)=>{
      for (const commande of res.data) {
        this.commandes.push(commande)
      }
    })
  }

  updateStatus(id:any, status: any) {
    this.commandeService.updateStatusCommande(id,status).subscribe((res)=> {
      if (res.status == "success"){
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: res.message,
          showConfirmButton: false,
          timer: 1500
        })
        this.refresh()
      }
    })
  }

  refresh(): void {
    window.location.reload();
  }
}
