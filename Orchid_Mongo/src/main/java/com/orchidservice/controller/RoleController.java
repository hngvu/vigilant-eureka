package com.orchidservice.controller;

import com.orchidservice.entity.Roles;
import com.orchidservice.service.RoleService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/roles")
public class RoleController  {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<Roles> getRoleById(@PathVariable("id") String id) {
        Roles roles = roleService.getRoleById(id);
        if (roles == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(roles);
    }
}
