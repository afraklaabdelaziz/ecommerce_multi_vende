import { Component } from '@angular/core';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-statistique',
  templateUrl: './statistique.component.html',
  styleUrls: ['./statistique.component.css']
})
export class StatistiqueComponent {
  numberOfRoom!:number;
  numberOfHotel!:number;
  numberOfUser!:number;
  constructor(private userService:UserService) {
  }

  ngOnInit(){
    this.numberRooms();
    this.numberUsers();
    this.numberHotels();
  }
  numberHotels(){
      this.numberOfHotel = 5
  }

  numberUsers(){
      this.numberOfUser = 5
  }

  numberRooms(){
      this.numberOfRoom =5
  }

  numberReservations(){

  }
}
