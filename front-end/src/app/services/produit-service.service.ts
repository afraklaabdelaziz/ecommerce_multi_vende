import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produit } from '../models/produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  constructor(private http:HttpClient) { }

  getAllProduits():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/produit/all")
  }

  getAllProduitOfVendor(email:string):Observable<any>{
    return this.http.get("http://localhost:8088/hotel/proprietair/"+email)
  }

  addProduit(produit:Produit):Observable<any>{
    return this.http.post<any>("http://localhost:8091/api/v1/produit/add",produit)
  }

  getOneProduit(id:number):Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/produit/one/"+id);
  }

  updateProduit(produit:Produit,id:number):Observable<any>{
    return this.http.put("http://localhost:8091/api/v1/produit/update/"+id,produit);
  }

  deleteProduit(id:number):Observable<any>{
    return this.http.delete("http://localhost:8091/api/v1/produit/delete/"+id)
  }

}
