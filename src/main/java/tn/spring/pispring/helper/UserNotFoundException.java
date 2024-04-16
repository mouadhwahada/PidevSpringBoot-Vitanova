package tn.spring.pispring.helper;

public class UserNotFoundException extends  Exception{

    public UserNotFoundException(){
        super ("User with this username not found in the db !!");
    }

    public UserNotFoundException(String msg){
        super(msg);
    }
}
