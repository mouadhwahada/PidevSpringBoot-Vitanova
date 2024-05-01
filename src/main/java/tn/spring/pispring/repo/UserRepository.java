package tn.spring.pispring.repo;


<<<<<<< HEAD
import tn.spring.pispring.Entities.User;
import  org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;


public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
=======


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);
    //User findByUsername(String username);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByRolesContains(Role role);
>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2


}
