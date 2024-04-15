package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.BadWord;
import tn.spring.pispring.Interfaces.BadWordInterface;
import tn.spring.pispring.Repositories.BadWordRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class BadWordService implements BadWordInterface {

    private BadWordRepo badWordRepo;

    @Override
    public List<BadWord> getAllBadWords() {
        return badWordRepo.findAll();
    }

    @Override
    public BadWord addBadWord(BadWord badWord) {
        return badWordRepo.save(badWord);
    }

    @Override
    public void deleteBadWord(Long id) {
        badWordRepo.deleteById(id);
    }
}