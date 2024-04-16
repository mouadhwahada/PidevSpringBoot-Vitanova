package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;

import java.util.List;

public interface RoleInterface {
    public Role createrole(Role role);
    List<Role> retrieveAllRoles();

    Role updateRole (Role role);

    Role retrieveRole (long idRole);
}
