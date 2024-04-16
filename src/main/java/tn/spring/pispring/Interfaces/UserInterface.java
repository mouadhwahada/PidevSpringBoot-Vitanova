package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.User;

import java.util.List;

public interface UserInterface {

    public User CreateUser(User user);

    List<User> retrieveAllUsers();

    User updateUser (User user);

    User retrieveUser (long idUser);


}
