import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {

  token: any;
  user: any;
  email: any;
  userFound: any;
  image: any;
  showModalAdd = false;
  

  constructor(private userService:UserService,private sanitizer: DomSanitizer) {}
  ngOnInit(): void {
    this.token = this.userService.getToken()
    this.user = this.userService.getUser(this.token);
    this.email = this.user.sub;
    this.userService.findUser(this.email).subscribe((res)=>{
      this.userFound = res.data
    })
  }

  toggleModalAdd() {
    this.showModalAdd = !this.showModalAdd;
  }

}
