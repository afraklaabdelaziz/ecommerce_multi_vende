import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { UserService } from 'src/app/services/user-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  token: any;
  email!:string
  user:any
  userFound: any;
  idUser!: number;
  url:any
  image: any

  constructor(private userService:UserService,private sanitizer: DomSanitizer) {}

  showModal = false;
  showModalAdd = false;

  toggleModal() {
    this.showModal = !this.showModal;
  }

  toggleModalAdd() {
    this.showModalAdd = !this.showModalAdd;
  }

  ngOnInit(): void {
    this.token = this.userService.getToken()
    this.user = this.userService.getUser(this.token);
    this.email = this.user.sub;
    this.userService.findUser(this.email).subscribe((res)=>{
      this.userFound = res.data
      this.idUser = res.data.id
      this.url ='data:image/png;base64,' + this.userFound.image.picByte;
      this.image = this.sanitizer.bypassSecurityTrustUrl(this.url)
    })
  }

  updateProfile(ngForm:NgForm) {
    this.userService.updateUser(this.userFound).subscribe((res)=>{
      console.log(res)
      if (res.status === 'success'){
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: res.message,
          showConfirmButton: false,
          timer: 1500
        })
        this.refresh()
      }
    })
  }

  refresh(): void {
    window.location.reload();
  }
}
