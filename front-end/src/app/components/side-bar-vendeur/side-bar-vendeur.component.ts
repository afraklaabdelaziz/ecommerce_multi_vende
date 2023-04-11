import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-side-bar-vendeur',
  templateUrl: './side-bar-vendeur.component.html',
  styleUrls: ['./side-bar-vendeur.component.css']
})
export class SideBarVendeurComponent {
  token:any;
  user:any;
  email!: string;
  userFound: any;
  url!: string;
  image: any;
  constructor(private userService:UserService,private sanitizer: DomSanitizer) {
  }
  ngOnInit(): void {
    this.token = this.userService.getToken()
    this.user = this.userService.getUser(this.token);
    this.email = this.user.sub;
    this.userService.findUser(this.email).subscribe((res)=>{
      this.userFound = res.data
      this.url ='data:image/png;base64,' + this.userFound.image.picByte;
      this.image = this.sanitizer.bypassSecurityTrustUrl(this.url)
    })
  }
  collapseShow = "hidden";
  toggleCollapseShow(classes: string) {
    this.collapseShow = classes;
  }
}
