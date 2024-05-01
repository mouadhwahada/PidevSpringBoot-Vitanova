package tn.spring.pispring.ServiceIMP.customer;

import tn.spring.pispring.dto.AbonnementDto;

import java.util.List;

public interface CustomerAbonnementService {

     List<AbonnementDto> getAllAbonnements();


    List<AbonnementDto> searchAbonnementByTitle(String title);
}
