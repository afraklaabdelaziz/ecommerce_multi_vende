package com.ecommerce.ecommerce_multi_vende.services.impl;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Role;
import com.ecommerce.ecommerce_multi_vende.repositories.RoleRepository;
import com.ecommerce.ecommerce_multi_vende.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseDto addRole(Role role) {
        if (role == null || role == new Role()){
            return new ResponseDto("bad request","role est null");
        }else {
            roleRepository.save(role);
            return new ResponseDto("success","role ete ajouter",role);
        }
    }
    @Override
    public ResponseDto findAllRole() {
        return new ResponseDto("success","roles",roleRepository.findAll());
    }
}
