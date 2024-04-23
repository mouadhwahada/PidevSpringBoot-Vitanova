package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.React;
import tn.spring.pispring.Entities.TypeReact;

import java.util.List;

public interface ReactInterface {
    List<React> getReactByPost(long idPost);

    React getReact(long idReact);


    React addReact(long idUser, long idPost, String type);

    TypeReact getTypeReact(long idUser, long idPost);

    void deleteReact(long idUser, long idPost);
}
