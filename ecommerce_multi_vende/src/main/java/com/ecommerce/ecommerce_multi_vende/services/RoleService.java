package com.ecommerce.ecommerce_multi_vende.services;

import com.ecommerce.ecommerce_multi_vende.dto.ResponseDto;
import com.ecommerce.ecommerce_multi_vende.entities.Role;

public interface RoleService {
    ResponseDto addRole(Role role);
    ResponseDto findAllRole();
}
