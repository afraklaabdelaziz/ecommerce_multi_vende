import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Commande } from 'src/app/models/commande';
import { CommandeService } from 'src/app/services/commande.service';

@Component({
  selector: 'app-commande-details',
  templateUrl: './commande-details.component.html',
  styleUrls: ['./commande-details.component.css']
})
export class CommandeDetailsComponent implements OnInit{
  constructor(private commandeService:CommandeService,private routeActive: ActivatedRoute) {
  }

  idCommande!:number
  commandeItems:any
  commande!:Commande
    ngOnInit(): void {
      this.idCommande = this.routeActive.snapshot.params['id'];
      this.commandeService.getOneCommande(this.idCommande).subscribe((res)=>{
        this.commande = res.data;
        this.commandeItems = res.data.commandeItemsList;
        console.log(res.data.commandeItemsList)
      })
    }

}
