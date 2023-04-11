import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Commande } from 'src/app/models/commande';
import { CommandeService } from 'src/app/services/commande.service';

@Component({
  selector: 'app-list-commande-client',
  templateUrl: './list-commande-client.component.html',
  styleUrls: ['./list-commande-client.component.css']
})
export class ListCommandeClientComponent implements OnInit{
  commandes:Commande[] = []
 constructor(private commandeService:CommandeService,private router:Router) {
 }

  ngOnInit(): void {
        this.commandeService.getAllCommandeOfClient().subscribe((res)=>{
          for (const commande of res.data) {
              this.commandes.push(commande.commande)
          }
        })
    }


  showDetails(id:any) {
      this.router.navigate(['commande-details/' + id])
  }
}
