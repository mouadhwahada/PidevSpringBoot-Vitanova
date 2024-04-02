package tn.spring.pispring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.pispring.Entities.Role;


public interface RoleRepository extends JpaRepository<Role,Long> {
}
