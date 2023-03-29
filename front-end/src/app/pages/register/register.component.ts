import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  ngOnInit(): void {
  }
  public user:User;
  constructor(private userService: UserService,private sanitizare:DomSanitizer,private router:Router) {
    this.user =  new User();
  }

  register(form:NgForm){
    this.userService.register(this.user).subscribe((res)=>{
      if (res.status == 'success'){
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: res.message,
          showConfirmButton: false,
          timer: 1500
        })
        if (this.userService.getToken() == null){
          this.router.navigate(['/auth/login'])
        }else {
          this.refresh()
        }

      }else{
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: res.message,
          showConfirmButton: true,
          timer: 2000
        })
      }
    })
  }

  refresh(): void {
    window.location.reload();
  }


  // imageSelected(event:any) {
  //   if (event.target.files){
  //     const file:File = event.target.files[0];
  //
  //     const image:Image = new Image (file,this.sanitizare.bypassSecurityTrustUrl(
  //       window.URL.createObjectURL(file)
  //     ))
  //     this.user.image.push(image)
  //   }
  // }

  // prepareFormData(user:User) : FormData{
  //   const formData = new FormData();
  //   formData.append(
  //     'user',
  //     new Blob([JSON.stringify(user)],{type: 'application/json'})
  //   );
  //   for (let i = 0; i < user.image.length; i++) {
  //     formData.append(
  //       'imageFile',
  //       user.image[i].file,
  //       user.image[i].file.name
  //     )
  //   }
  //   return formData
  // }
}
