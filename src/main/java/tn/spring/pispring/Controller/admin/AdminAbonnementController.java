package tn.spring.pispring.Controller.admin;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.ServiceIMP.admin.adminabonnement.AdminAbonnementService;
import tn.spring.pispring.ServiceIMP.admin.faq.FAQService;
import tn.spring.pispring.ServiceIMP.customer.cart.CartService;
import tn.spring.pispring.dto.AbonnementDto;
import tn.spring.pispring.dto.AddAbonnementInCartDto;
import tn.spring.pispring.dto.FAQDto;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminAbonnementController {

    private final AdminAbonnementService adminAbonnementService;

    private final FAQService faqService;

    @PostMapping("abonnement")
    public ResponseEntity<AbonnementDto> addAbonnement(@RequestBody AbonnementDto abonnementDto) throws IOException {
        AbonnementDto abonnementDto1 = adminAbonnementService.addAbonnement(abonnementDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(abonnementDto1);
    }




    @GetMapping("/abonnements")
    public ResponseEntity<List<AbonnementDto>> getAllAbonnements(){
        List<AbonnementDto> abonnementDtos = adminAbonnementService.getAllAbonnements();
        return  ResponseEntity.ok(abonnementDtos);
    }


    @GetMapping("/dashboard/{name}")
    public ResponseEntity<List<AbonnementDto>> getAllAbonnementByName(@PathVariable String name){
        List<AbonnementDto> abonnementDtos = adminAbonnementService.getAllAbonnementbyName(name);
        return  ResponseEntity.ok(abonnementDtos);
    }

    @DeleteMapping("/abonnement/{abonnementId}")
    public ResponseEntity<Void> deleteAbonnement(@PathVariable Long abonnementId){
        boolean deleted = adminAbonnementService.deleteAbonnement(abonnementId);

        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/abonnement/{abonnementId}")
    public ResponseEntity<AbonnementDto> getAbonnementById(@PathVariable Long abonnementId){
        AbonnementDto abonnementDto=adminAbonnementService.getAbonnementById(abonnementId);
        if(abonnementDto !=null){
            return ResponseEntity.ok(abonnementDto);

        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/abonnement/{abonnementId}")
    public ResponseEntity<AbonnementDto> updateAbonnement(@PathVariable Long abonnementId, @RequestBody AbonnementDto abonnementDto) throws IOException {
        AbonnementDto updateAbonnement = adminAbonnementService.updateAbonnement(abonnementId, abonnementDto);
        if(updateAbonnement !=null){
            return ResponseEntity.ok(updateAbonnement);

        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/faq/{abonnementId}")
    public ResponseEntity<FAQDto> postFAQ(@PathVariable Long abonnementId, @RequestBody FAQDto faqDto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(abonnementId,faqDto));
    }

}
