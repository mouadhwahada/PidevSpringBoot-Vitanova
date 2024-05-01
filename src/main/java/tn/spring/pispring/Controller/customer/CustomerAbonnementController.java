package tn.spring.pispring.Controller.customer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.spring.pispring.ServiceIMP.customer.CustomerAbonnementService;
import tn.spring.pispring.dto.AbonnementDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerAbonnementController {

private final CustomerAbonnementService customerAbonnementService;


    @GetMapping("/abonnements")
    public ResponseEntity<List<AbonnementDto>> getAllAbonnements(){
        List<AbonnementDto> abonnementDtos = customerAbonnementService.getAllAbonnements();
        return  ResponseEntity.ok(abonnementDtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<AbonnementDto>> getAllAbonnementByName(@PathVariable String name){
        List<AbonnementDto> abonnementDtos = customerAbonnementService.searchAbonnementByTitle(name);
        return  ResponseEntity.ok(abonnementDtos);
    }


}
