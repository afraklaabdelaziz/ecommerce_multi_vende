package com.ecommerce.ecommerce_multi_vende.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Adresse;
import com.ecommerce.ecommerce_multi_vende.entities.Role;
import com.ecommerce.ecommerce_multi_vende.entities.UserApp;
import com.ecommerce.ecommerce_multi_vende.repositories.UserRepository;
import com.ecommerce.ecommerce_multi_vende.services.AdresseService;
import com.ecommerce.ecommerce_multi_vende.services.RoleService;
import com.ecommerce.ecommerce_multi_vende.services.UserServices;
import com.ecommerce.ecommerce_multi_vende.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private RoleService roleService;
    private AdresseService adresseService;
    private UserServices userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        roleService = mock(RoleService.class);
        adresseService = mock(AdresseService.class);
    }

    @Test
    public void testAddUserWithValidInput() {
        UserApp userApp = new UserApp();
        userApp.setNom("John Doe");
        userApp.setMotDePasse("password");
        userApp.setTelephone("123456789");
        userApp.setEmail("john.doe@example.com");
        Adresse adresse = new Adresse();
        adresse.setVille("Paris");
        adresse.setCodePostal("75000");
        adresse.setPays("France");
        userApp.setAdresse(adresse);
        Role role = new Role();
        role.setId(2L);
        role.setNom("ROLE_USER");
        when(roleService.findById(2L)).thenReturn(new ResponseDto("success","role", role));
        when(userRepository.findByEmail(userApp.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByTelephone(userApp.getTelephone())).thenReturn(Optional.empty());

        ResponseDto response = userService.addUser(userApp);

        assertEquals("success", response.getStatus());
        assertEquals("votre compte a ete cree avec success", response.getMessage());
        assertEquals(userApp, response.getData());
    }

    @Test
    public void testAddUserWithNullInput() {
        UserApp userApp = null;

        ResponseDto response = userService.addUser(userApp);

        assertEquals("Bad request", response.getStatus());
        assertEquals("ce user est null", response.getMessage());
    }

    // add more test cases for the other error conditions here
}
