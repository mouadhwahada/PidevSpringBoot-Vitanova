package tn.spring.pispring.repo;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.pispring.Entities.Role;


public interface RoleRepository extends JpaRepository<Role,Long> {
=======



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.dto.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName );

>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2
}
