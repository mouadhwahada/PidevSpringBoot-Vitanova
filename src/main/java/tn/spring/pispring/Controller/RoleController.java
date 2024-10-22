package tn.spring.pispring.Controller;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Interfaces.RoleInterface;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {
    RoleInterface roleInterface;

    @PostMapping("/createrole")
    public Role createrole(Role role) {
        return roleInterface.createrole(role);
    }

    @GetMapping("/retrieveAllRoles")
    public List<Role> retrieveAllRoles() {
        return roleInterface.retrieveAllRoles();
    }
    @PutMapping("/updaterole")
    public Role updateRole(@RequestBody Role role) {
        return roleInterface.updateRole(role);
    }

    @DeleteMapping("/retrieveRole/{idRole}")
    public Role retrieveRole(@PathVariable("idRole")long idRole) {
        return roleInterface.retrieveRole(idRole);
    }
=======

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.ServiceIMP.RoleServiceIMP;
import tn.spring.pispring.dto.RoleName;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class RoleController  {
@Autowired
RoleServiceIMP roleServiceIMP;

    @PostMapping ("/addRole/{RolesName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  void addRole(@PathVariable("RolesName") RoleName roleName){
        roleServiceIMP.addRole(roleName);
    }
    @DeleteMapping("/deleteRole/{RolesName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRole(@PathVariable("RolesName") RoleName roleName) {
        roleServiceIMP.deleteRole(roleName);
    }
    @GetMapping ("/getAllRoles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Role> getAllRoles() {
        return roleServiceIMP.getAllRoles();
    }
    @PostMapping("/AddALLRoles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void AddALLRoles() {
        roleServiceIMP.AddALLRoles();
    }


>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2
}
