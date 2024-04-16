
package tn.spring.pispring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Entities.UserRole;
import tn.spring.pispring.Service.UserService;
import tn.spring.pispring.helper.UserFoundException;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception{


        user.setProfile("default.png");
        //encoding password withbcryptpasswordencoder

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role=new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole= new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);


        return this.userService.createUser(user,roles);


    }
    @GetMapping("/{lastname}")
    public User getUser(@PathVariable("lastname") String lastname)
    {
        return this.userService.getUser(lastname);
    }

    //delete user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);

    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        return exceptionHandler(ex);

package tn.spring.pispring.Controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaces.UserInterface;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    UserInterface userInterface;

    @PostMapping("/createuser")
    public User CreateUser(User user) {
        return userInterface.CreateUser(user);
    }

    @GetMapping("/retrieveAllUsers")
    public List<User> retrieveAllUsers() {
        return userInterface.retrieveAllUsers();
    }
    @PutMapping("/updateuser")
    public User updateUser(@RequestBody User user) {
        return userInterface.updateUser(user);
    }

    @DeleteMapping("/retrieveUser/{idUser}")
    public User retrieveUser(@PathVariable("idUser")long idUser) {
        return userInterface.retrieveUser(idUser);

    }



    }





}
