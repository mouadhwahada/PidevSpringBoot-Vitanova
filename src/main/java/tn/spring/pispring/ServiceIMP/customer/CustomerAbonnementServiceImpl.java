package tn.spring.pispring.ServiceIMP.customer;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Abonnement;
import tn.spring.pispring.dto.AbonnementDto;
import tn.spring.pispring.repo.AbonnementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerAbonnementServiceImpl implements  CustomerAbonnementService{

    private AbonnementRepository abonnementRepository;


    public List<AbonnementDto> getAllAbonnements(){
        List<Abonnement> abonnements = abonnementRepository.findAll();
        return abonnements.stream().map(Abonnement::getDto).collect(Collectors.toList());
    }

    public List<AbonnementDto> searchAbonnementByTitle(String name){
        List<Abonnement> abonnements = abonnementRepository.findAllByNameContaining(name);
        return abonnements.stream().map(Abonnement::getDto).collect(Collectors.toList());
    }
}
