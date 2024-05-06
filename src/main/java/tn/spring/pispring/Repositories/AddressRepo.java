package tn.spring.pispring.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Address;
import tn.spring.pispring.dto.RoleName;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {
    @Query("SELECT a FROM Address a JOIN a.user u JOIN u.roles r WHERE r.name = :roleName")
    List<Address> findByUserRole(@Param("roleName") RoleName roleName);


    @Query("SELECT a FROM Address a WHERE a.user.id = :userId")
    Address findByUserId(@Param("userId") Long userId);


}