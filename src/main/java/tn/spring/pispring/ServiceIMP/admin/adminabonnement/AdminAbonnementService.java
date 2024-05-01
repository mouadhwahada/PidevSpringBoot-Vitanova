package tn.spring.pispring.ServiceIMP.admin.adminabonnement;

import tn.spring.pispring.Entities.Abonnement;
import tn.spring.pispring.dto.AbonnementDto;

import java.io.IOException;
import java.util.List;

public interface AdminAbonnementService {



    AbonnementDto addAbonnement(AbonnementDto abonnementDto) throws IOException;

  //  public Abonnement addAbonnement(AbonnementDto abonnementDto);
    List<AbonnementDto> getAllAbonnements();

     List<AbonnementDto> getAllAbonnementbyName(String name);

    boolean deleteAbonnement(Long id);

    AbonnementDto getAbonnementById(Long abonnementId);

    AbonnementDto updateAbonnement(Long abonnementId,AbonnementDto abonnementDto) throws IOException;
}
