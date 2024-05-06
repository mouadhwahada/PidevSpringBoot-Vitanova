package tn.spring.pispring.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.dto.RoleName;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> findByRoleName(@Param("roleName") RoleName roleName);

    User findByPhoneNumber(int phoneNumber);


    @Query("SELECT u FROM User u JOIN u.roles r WHERE u.phoneNumber = :phoneNumber AND r.name = :roleName")
    User findByPhoneNumberAndRole(@Param("phoneNumber") int phoneNumber, @Param("roleName") RoleName roleName);


}
