package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.DemandeVendeur;
import com.ecommerce.ecommerce_multi_vende.entities.Role;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import com.ecommerce.ecommerce_multi_vende.repositories.UserRepository;
import com.ecommerce.ecommerce_multi_vende.services.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {
    private UserRepository userRepository;
    private AdresseService adresseService;
    private DemmandeService demmandeService;
    private RoleService roleService;
    private MailService mailService;
    private PdfGeneratorService pdfGenerator;
    public UserServiceImpl(UserRepository userRepository, AdresseService adresseService, DemmandeService demmandeService, RoleService roleService, MailService mailService,PdfGeneratorService pdfGenerator) {
        this.userRepository = userRepository;
        this.adresseService = adresseService;
        this.demmandeService = demmandeService;
        this.roleService = roleService;
        this.mailService = mailService;
        this.pdfGenerator = pdfGenerator;
    }

    @Override
    public ResponseDto addUser(UserApp userApp){
        if (userApp == null ){
            return new ResponseDto("Bad request","ce user est null");
        }else if(userApp.getNom() == "" || userApp.getMotDePasse() == ""
                || userApp.getTelephone() == "" || userApp.getEmail() == ""){
            return new ResponseDto("Bad request","compliter les information de user");
        }else if (userRepository.findByEmail(userApp.getEmail()).isPresent()){
            return new ResponseDto("Bad request","cet email a deja existe");
        } else if (userRepository.findByTelephone(userApp.getTelephone()).isPresent()) {
            return new ResponseDto("bad request","cet telephone a deja utilise");
        } else {
            userApp.setMotDePasse(new BCryptPasswordEncoder().encode(userApp.getMotDePasse()));
            userApp.setVendor(false);
           Optional<Role> role = (Optional<Role>) roleService.findById(2L).getData();
            userApp.getRoles().add(role.get());
            adresseService.addAdresse(userApp.getAdresse());
            userRepository.save(userApp);
           // mailService.sendEmail(userApp.getEmail(),"votre compte a ete cree avec success","Creation de compte","zzz");
            return new ResponseDto("success","votre compte a ete cree avec success",userApp);
        }
    }


    @Override
    public ResponseDto findByEmail(String email){
        Optional<UserApp> userApp = userRepository.findByEmail(email);
        if (!userApp.isPresent()){
            return new ResponseDto("bad request","email est incorrect");
        }
        User user =  new User(userApp.get().getEmail(),userApp.get().getMotDePasse(),getAuthorities(userApp.get().getRoles()));
        return new ResponseDto("success","user found",user);
    }

    @Override
    public ResponseDto findByTelephone(String telephone) {
        Optional<UserApp> user = userRepository.findByTelephone(telephone);
        if (!user.isPresent()){
            return new ResponseDto("bad requeste","user n'exist pas");
        }else {
            return new ResponseDto("success","user",user.get());
        }
        
    }

    @Override
    public ResponseDto findAll() {
        return new ResponseDto("success","All users",userRepository.findAll());
    }

    @Override
    public ResponseDto findUserAppByEmail(String email) {
        Optional<UserApp> user = userRepository.findByEmail(email);
        if (!user.isPresent()){
            return new ResponseDto("bad requeste","user n'exist pas");
        }else {
            return new ResponseDto("success","user",user.get());
        }

    }

    @Override
    public ResponseDto updatUser(UserApp userApp) {
        if (userApp == null ){
            return new ResponseDto("Bad request","ce user est null");
        }else if(userApp.getNom() == "" || userApp.getMotDePasse() == ""
                || userApp.getTelephone() == "" || userApp.getEmail() == ""){
            return new ResponseDto("Bad request","compliter les information de user");
        } else {
            Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = (User) principale;
                Optional<UserApp> userFound =  userRepository.findByEmail(user.getUsername());
                userFound.get().setNom(userApp.getNom());
                userFound.get().setPrenom(userApp.getPrenom());
                userFound.get().setEmail(userApp.getEmail());
                userFound.get().setTelephone(userApp.getTelephone());
                userFound.get().setAdresse(userApp.getAdresse());
            adresseService.addAdresse(userFound.get().getAdresse());
            userRepository.save(userApp);
            // mailService.sendEmail(userApp.getEmail(),"votre compte a ete cree avec success","Creation de compte","zzz");
            return new ResponseDto("success","votre compte a ete cree avec success",userFound);
        }
    }

    @Override
    public ResponseDto devenirVendeur(DemandeVendeur demandeVendeur) {
        Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principale instanceof User){
          User user = (User) principale;
          Optional<UserApp> userApp =  userRepository.findByEmail(user.getUsername());
            demandeVendeur.setUserApp(userApp.get());
            demmandeService.addDemmande(demandeVendeur);
          return new ResponseDto("success","vous avez maintenant vendeur",userApp);
        }else {
            return new ResponseDto("bad request","user n'est pas authentifier");
        }
    }

    @Override
    public ResponseDto responseDemmandeVendeur(Boolean response,Long idDemmande) {
        if (response){
            Optional<DemandeVendeur> demandeVendeur = (Optional<DemandeVendeur>)  demmandeService.findDemmandeById(idDemmande).getData();
             Optional<UserApp> userApp = userRepository.findByEmail(demandeVendeur.get().getUserApp().getEmail());
             userApp.get().setVendor(true);
             Optional<Role> role = (Optional<Role>) roleService.findById(3L).getData();
             userApp.get().getRoles().add(role.get());
             userRepository.save(userApp.get());
             demmandeService.deleteDemmande(idDemmande);
            return new ResponseDto("success","vous avez acceptez demmande");
        }else {
            demmandeService.deleteDemmande(idDemmande);
            return new ResponseDto("success","demmande est refuser");
        }

    }


    @Override
    public Long count() {
        return userRepository.count();
    }


    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        return getGrantedAuthorities(roles);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNom()));
        }
        return authorities;
    }



}
