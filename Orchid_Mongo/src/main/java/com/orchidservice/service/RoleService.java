package com.orchidservice.service;

import com.orchidservice.entity.Roles;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {
    public List<Roles> getAllRoles();
    public Roles getRoleById(String id);
    public void createRole(Roles role);
    public void updateRole(String id, Roles role);
    public void deleteRole(String id);
    public Roles getRoleByName(String roleName);
}
