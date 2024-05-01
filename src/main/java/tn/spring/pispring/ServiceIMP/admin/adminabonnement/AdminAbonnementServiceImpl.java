package tn.spring.pispring.ServiceIMP.admin.adminabonnement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Abonnement;
import tn.spring.pispring.Entities.Category;
import tn.spring.pispring.dto.AbonnementDto;
import tn.spring.pispring.repo.AbonnementRepository;
import tn.spring.pispring.repo.CategoryRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminAbonnementServiceImpl implements AdminAbonnementService {


    private final AbonnementRepository abonnementRepository;

    private final CategoryRepository categoryRepository;

    /*

     */

    public AbonnementDto addAbonnement(AbonnementDto abonnementDto) throws IOException {
        Abonnement abonnement = new Abonnement();

        abonnement.setName(abonnementDto.getName());
        abonnement.setDecription(abonnementDto.getDecription());
        abonnement.setPrice(abonnementDto.getPrice());
        abonnement.setImg(abonnementDto.getByteImg());
        //.getBytes());

        Category category = categoryRepository.findById(abonnementDto.getCategoryId()).orElseThrow();

        abonnement.setCategory(category);

        return abonnementRepository.save(abonnement).getDto();

    }

    public List<AbonnementDto> getAllAbonnements() {
        List<Abonnement> abonnements = abonnementRepository.findAll();
        return abonnements.stream().map(Abonnement::getDto).collect(Collectors.toList());
    }

    public List<AbonnementDto> getAllAbonnementbyName(String name) {
        List<Abonnement> abonnements = abonnementRepository.findAllByNameContaining(name);
        return abonnements.stream().map(Abonnement::getDto).collect(Collectors.toList());
    }

    public boolean deleteAbonnement(Long id) {
        Optional<Abonnement> optionalAbonnement = abonnementRepository.findById(id);
        if (optionalAbonnement.isPresent()) {
            abonnementRepository.deleteById(id);
            return true;

        }
        return false;
    }


    public AbonnementDto getAbonnementById(Long abonnementId) {
        Optional<Abonnement> optionalAbonnement = abonnementRepository.findById(abonnementId);
        if (optionalAbonnement.isPresent()) {
            return optionalAbonnement.get().getDto();
        } else {
            return null;
        }
    }

    public AbonnementDto updateAbonnement(Long abonnementId, AbonnementDto abonnementDto) throws IOException {
        Optional<Abonnement> optionalAbonnement = abonnementRepository.findById(abonnementId);
        Optional<Category> optionalCategory = categoryRepository.findById(abonnementDto.getCategoryId());
        if (optionalAbonnement.isPresent() && optionalCategory.isPresent()) {

            Abonnement abonnement = optionalAbonnement.get();

            abonnement.setName(abonnementDto.getName());
            abonnement.setPrice(abonnementDto.getPrice());
            abonnement.setDecription(abonnementDto.getDecription());
            abonnement.setCategory(optionalCategory.get());

        if (abonnementDto.getByteImg() != null) {
                abonnement.setImg(abonnementDto.getByteImg());
            }
            return abonnementRepository.save(abonnement).getDto();
        } else {
            return null;
        }
         /*   return abonnementRepository.save(abonnement).getDto();

        }
 return  null;
    }*/
    }
}


