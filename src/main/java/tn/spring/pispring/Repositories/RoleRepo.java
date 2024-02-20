package tn.spring.pispring.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
}
