import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/app/models/login';
import { UserService } from 'src/app/services/user-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public login: Login;
  roles:String[] = []

  constructor(private userService:UserService,public router:Router) {
    this.login = new Login();
  }

  authenticate(){
    this.userService.login(this.login).subscribe((res:any)=> {
        if (res.status != "success"){
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: res.message,
            showConfirmButton: true,
            timer: 2000
          })

        }else {
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: "welcome to our account",
            showConfirmButton: false,
            timer: 2000
          })
          localStorage.setItem("token",res.data)
          let roles = this.userService.getUser(res.data).authorities
          for (let i = 0 ; i < roles.length ; i++) {
            this.roles.push(roles[i].authority)
          }
          if (this.roles.includes("admin")){
            this.router.navigate(["/admin"])
          }else if (this.roles.includes("vendeur")){
            this.router.navigate(["/vendeur"])
          }else {
            this.router.navigate([''])
          }
          // switch (this.role) {
          //   case "client" :
          //     this.router.navigate([""]);
          //     break;
          //   case "admin" :
          //     this.router.navigate(["/admin"]);
          //     break;
          //   case "proprietaire" :
          //     this.router.navigate(["/owner"]);
          //     break;
          // }
        }

      }

    )
  }

}
