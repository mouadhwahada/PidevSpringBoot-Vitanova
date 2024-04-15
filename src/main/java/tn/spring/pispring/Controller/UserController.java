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
