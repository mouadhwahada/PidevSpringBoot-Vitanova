package tn.spring.pispring.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Interfaces.RoleInterface;
import tn.spring.pispring.Repositories.RoleRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService implements RoleInterface {

    RoleRepo roleRepo;

    @Override
    public List<Role> retrieveAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Role retrieveRole(long idRole) {
        return roleRepo.findById(idRole).orElse(null);
    }

    @Override
    public Role createrole(Role role) {
        return roleRepo.save(role);
    }
}
