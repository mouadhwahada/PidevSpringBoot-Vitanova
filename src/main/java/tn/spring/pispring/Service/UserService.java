package tn.spring.pispring.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Entities.UserRole;

import java.util.Map;
import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user , Set<UserRole> userRoles) throws Exception;

    //get User by lastname
    public User getUser(String lastname);

    //delete user by id
    public void deleteUser(Long userId);


}
