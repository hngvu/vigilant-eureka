package com.orchidservice.service.Impl;

import com.orchidservice.entity.Roles;
import com.orchidservice.repository.RoleRepository;
import com.orchidservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Roles> getAllRoles() {
        List<Roles> roles =  roleRepository.findAll();
        return roles; // Replace with actual implementation
    }

    @Override
    public Roles getRoleById(String id) {
        Optional<Roles> role = roleRepository.findById(id);
        if (!role.isPresent()) {
            return null;
        }
        Roles roles = role.get();
        return roles;
    }

    @Override
    public void createRole(Roles role) {
        if (role == null || role.getRoleName() == null || role.getRoleName().isEmpty()) {
            throw new IllegalArgumentException("Role or role name cannot be null or empty");
        }
        roleRepository.save(role);


    }

    @Override
    public void updateRole(String id, Roles role) {
    }

    @Override
    public void deleteRole(String id) {

    }

    @Override
    public Roles getRoleByName(String roleName) {
        Roles role = roleRepository.findByRoleName(roleName);
        return role;
    }
}
