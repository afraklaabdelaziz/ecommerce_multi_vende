import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user-service.service';


@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  token!: any
  roles: String[] = []
  constructor(private userService: UserService,private router:Router) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    this.token = this.userService.getToken();
    
    let roles = this.userService.getUser(this.token).authorities
    for (let i = 0 ; i < roles.length ; i++) {
      this.roles.push(roles[i].authority)
    }
    
    const authorized =  this.roles.includes(route.data['role'])
    if (!authorized){
      this.router.navigate(['auth/login'])
    }
    return authorized;
  }

}
