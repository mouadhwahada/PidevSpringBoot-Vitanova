package tn.spring.pispring.Controller;

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
}
