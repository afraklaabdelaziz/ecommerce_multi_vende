import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DemmandeVendeur } from '../models/demmande-vendeur';

@Injectable({
  providedIn: 'root'
})
export class DemmandeVendeurService {
  constructor(private http:HttpClient) { }

  getAllDemmande():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/demmande_vendeur/all")
  }

  addDemmande(demmande:DemmandeVendeur):Observable<any>{
    return this.http.post<any>("http://localhost:8091/api/v1/demmande_vendeur/add",demmande)
  }
  
  responceDemmande(response:boolean,id:number):Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/user/response-demmande/"+response+"/"+id)
  }
  
  count():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/demmande_vendeur/count")
  }

}
