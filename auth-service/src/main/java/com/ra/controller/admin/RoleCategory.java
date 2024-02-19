package com.ra.controller.admin;

import com.ra.model.entity.Role;
import com.ra.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/roles")
public class RoleCategory {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        List<Role> roles = roleService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
