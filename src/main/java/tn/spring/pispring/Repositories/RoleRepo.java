package tn.spring.pispring.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.dto.RoleName;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(RoleName name);

}
