import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit{
  constructor(private userService:UserService,private router:Router) {
  }

  ngOnInit(): void {
    this.userService.logout().subscribe((res) => {
      localStorage.removeItem('token')
      if (res.status == "success"){
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: res.message,
          showConfirmButton: true,
          timer: 2000
        })
        this.router.navigate(['/auth/login'])
      }

    })
  }

}
