package tn.spring.pispring.helper;

public class UserFoundException extends Exception{

    public UserFoundException(){
        super
                ("User with this username is already there in the db");
    }
    public UserFoundException(String msg){
        super(msg);
    }
}
