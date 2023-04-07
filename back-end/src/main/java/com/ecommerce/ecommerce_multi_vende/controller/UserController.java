package com.ecommerce.ecommerce_multi_vende.controller;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.DemandeVendeur;
import com.ecommerce.ecommerce_multi_vende.services.PdfGeneratorService;
import com.ecommerce.ecommerce_multi_vende.services.UserServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserServices userServices;
    private PdfGeneratorService pdfGenerator;

    public UserController(UserServices userServices, PdfGeneratorService pdfGenerator) {
        this.userServices = userServices;
        this.pdfGenerator = pdfGenerator;
    }

    @PostMapping("/devenir_vendeur")
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
    @GetMapping("/all")
    public ResponseDto allUsers(){
        return userServices.findAll();
    }

    @GetMapping("/oneUser/{email}")
    public ResponseDto findUserByEmail(@PathVariable String email){
        return userServices.findUserAppByEmail(email);
    }

    @GetMapping("/pdf")
    public void generetePdf(HttpServletResponse response) throws IOException {
        response.setContentType("Application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String curentDateTime = dateFormat.format(new Date());
        String headKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_"+curentDateTime + ".pdf";
        response.setHeader(headKey,headerValue);
        pdfGenerator.export(response);
    }

}
