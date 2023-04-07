import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router} from '@angular/router';
import {map, Observable } from 'rxjs';
import { Login } from '../models/login';
import { User } from '../models/user';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,private router:Router) { }

  getAllUsers():Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/user/all")
  }

  register(user:User):Observable<any>{
    return this.http.post<any>(`http://localhost:8091/api/v1/authenticate/register`,user)
  }


  bannUser(id:number):Observable<any>{
    return this.http.put("http://localhost:8088/user/bannUser/"+id,null)
  }

  deleteUser(id:number):Observable<any>{
    return this.http.delete("http://localhost:8088/user/delete/"+id)
  }

  updateUser(id:number,user:User):Observable<any>{
    return this.http.put("http://localhost:8091/aoi/v1/user/update/"+id,user);
  }

  findUser(email:string):Observable<any>{
    return this.http.get("http://localhost:8091/api/v1/user/oneUser/"+email)
  }

  login(login:any):Observable<any> {
    return this.http.post(`http://localhost:8091/api/v1/authenticate/login`,login)
  }
  logout():Observable<any>{
    return this.http.post(`http://localhost:8091/api/v1/user/logout`,null)
  }


  getUser(token:string):any{
    return JSON.parse(atob(token.split('.')[1]))
  }

  getToken(){
    return  localStorage.getItem("token")
  }
}


