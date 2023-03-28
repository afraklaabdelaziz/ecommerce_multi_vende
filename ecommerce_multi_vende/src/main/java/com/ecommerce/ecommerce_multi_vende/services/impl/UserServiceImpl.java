package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Role;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import com.ecommerce.ecommerce_multi_vende.repositories.UserRepository;
import com.ecommerce.ecommerce_multi_vende.services.*;
import org.aspectj.weaver.ast.Instanceof;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {
    private UserRepository userRepository;
    private AdresseService adresseService;
    private RoleService roleService;
    private MailService mailService;

    public UserServiceImpl(UserRepository userRepository, AdresseService adresseService,RoleService roleService,MailService mailService) {
        this.userRepository = userRepository;
        this.adresseService = adresseService;
        this.roleService = roleService;
        this.mailService = mailService;
    }

    @Override
    public ResponseDto addUser(UserApp userApp) {
        if (userApp == null ){
            return new ResponseDto("Bad request","ce user est null");
        }else if(userApp.getNom() == null || userApp.getMotDePasse() == null
                || userApp.getTelephone() == null || userApp.getEmail() == null){
            return new ResponseDto("Bad request","compliter les information de user");
        }else if (userRepository.findByEmail(userApp.getEmail()).isPresent()){
            return new ResponseDto("Bad request","cet email a deja existe");
        } else if (userRepository.findByTelephone(userApp.getTelephone()).isPresent()) {
            return new ResponseDto("bad request","cet telephone a deja utilise");
        } else {
            userApp.setMotDePasse(new BCryptPasswordEncoder().encode(userApp.getMotDePasse()));
            userApp.setVendor(false);
            adresseService.addAdresse(userApp.getAdresse());
            userRepository.save(userApp);
            mailService.sendEmail(userApp.getEmail(),"votre compte a ete cree avec success","Creation de compte");
            return new ResponseDto("success","votre compte a ete cree avec success",userApp);
        }
    }


    @Override
    public UserDetails findByEmail(String email){
        Optional<UserApp> userApp = userRepository.findByEmail(email);
        return new User(userApp.get().getEmail(),userApp.get().getMotDePasse(),getAuthorities(userApp.get().getRoles()));
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
    public ResponseDto devenirVendeur() {
        Object principale = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principale instanceof User){
          User user = (User) principale;
          Optional<UserApp> userApp =  userRepository.findByEmail(user.getUsername());
          userApp.get().setVendor(true);
          Role role = (Role) roleService.findById(3L).getData();
          userApp.get().getRoles().add(role);
          userRepository.save(userApp.get());
          return new ResponseDto("success","vous avez maintenant vendeur",userApp);
        }else {
            return new ResponseDto("bad request","user n'est pas authentifier");
        }
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
