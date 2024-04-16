package tn.spring.pispring.repo;


import tn.spring.pispring.Entities.User;
import  org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;


public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);


}
