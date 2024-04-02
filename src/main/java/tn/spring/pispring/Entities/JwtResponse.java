package tn.spring.pispring.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    String token;

    public JwtResponse(){

    }
    public JwtResponse(String token){
        this.token=token;

    }

}
