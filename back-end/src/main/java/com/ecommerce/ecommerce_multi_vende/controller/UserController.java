package com.ecommerce.ecommerce_multi_vende.controller;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.DemandeVendeur;
import com.ecommerce.ecommerce_multi_vende.services.UserServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
    @PostMapping("devenir_vendeur")
    public ResponseDto devenirVendeur(@RequestBody DemandeVendeur demandeVendeur){
        return userServices.devenirVendeur(demandeVendeur);
    }

    @GetMapping("/response-demmande/{response}/{idDemmande}")
    public ResponseDto responseVendeur(@PathVariable Boolean response,@PathVariable Long idDemmande){
        return userServices.responseDemmandeVendeur(response,idDemmande);
    }
    @PostMapping("/logout")
    public ResponseDto logout() {
        return new ResponseDto("success","Au revoir");
    }

    @GetMapping("/oneUser/{email}")
    public ResponseDto findUserByEmail(@PathVariable String email){
        return userServices.findUserAppByEmail(email);
    }

}
