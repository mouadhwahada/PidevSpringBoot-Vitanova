package tn.spring.pispring.Service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Entities.UserRole;
import tn.spring.pispring.Service.UserService;
import tn.spring.pispring.helper.UserFoundException;
import tn.spring.pispring.helper.UserNotFoundException;
import tn.spring.pispring.repo.RoleRepository;
import tn.spring.pispring.repo.UserRepository;


import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;



    //creating user

    @Override

    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is already there !!");
            throw new UserFoundException();

        } else {

            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }


        return local;
    }
    @Override
    public  User getUser(String username){
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }



}
