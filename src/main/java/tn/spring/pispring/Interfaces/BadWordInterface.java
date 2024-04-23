package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.BadWord;

import java.util.List;

public interface BadWordInterface {
    List<BadWord> getAllBadWords();

    BadWord addBadWord(BadWord badWord);

    void deleteBadWord(Long id);
}
