import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produit } from '../models/produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  constructor(private http:HttpClient) { }

  getAllProduits(page:number):Observable<any>{
    return this.http.get(`http://localhost:8091/api/v1/produit/all?size=6&page=${page}`)
  }

  getAllProduitOfVendor(page:number):Observable<any>{
    return this.http.get(`http://localhost:8091/api/v1/produit/all-of-vendeur?size=6&page=${page}`)
  }

  addProduit(produit:Produit):Observable<any>{
    return this.http.post<any>("http://localhost:8091/api/v1/produit/add",produit)
  }

  getOneProduit(id:number):Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/produit/one/"+id);
  }

  updateProduit(produit:Produit):Observable<any>{
    return this.http.put("http://localhost:8091/api/v1/produit/update",produit);
  }

  deleteProduit(id:number):Observable<any>{
    return this.http.delete("http://localhost:8091/api/v1/produit/delete/"+id)
  }
  countByVendeur():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/produit/count-by-vendeur")
  }
  count():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/produit/count")
  }
}
