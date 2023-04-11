import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Commande } from '../models/commande';

@Injectable({
  providedIn: 'root'
})
export class CommandeService {
  constructor(private http:HttpClient) { }

  getAllCommmande():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/commande/all")
  }

  getAllCommandeOfClient():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/user/facture")
  }

  addCommande(commande:Commande):Observable<any>{
    return this.http.post<any>("http://localhost:8091/api/v1/commande/add",commande)
  }

  getOneCommande(id:number):Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/commande/one/"+id);
  }
  count():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/commande/count")
  }
  countVendeur():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/commande/count-of-vendeur")
  }
  updateStatusCommande(id:any,status:any):Observable<any>{
    return this.http.put("http://localhost:8091/api/v1/commande/update-status/"+id+"/"+status,null)
  }
}
