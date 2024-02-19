package com.ra.service.role;

import com.ra.model.entity.Role;
import com.ra.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceIMPL implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(()->new RuntimeException("Role not found"));
    }
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
