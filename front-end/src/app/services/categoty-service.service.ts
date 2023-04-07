import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategotyServiceService {
  constructor(private http:HttpClient) { }
  

  getAllGategories():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/categorie/all")
  }
}
