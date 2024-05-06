package tn.spring.pispring.Services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaces.UserInterface;
import tn.spring.pispring.Repositories.RoleRepo;
import tn.spring.pispring.Repositories.UserRepo;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserInterface {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @Override
    public User CreateUser(User user) {
        return userRepo.save(user);
    }


    @Override
    public List<User> retrieveAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User retrieveUser(long idUser) {
        return userRepo.findById(idUser).orElse(null);
    }





}





