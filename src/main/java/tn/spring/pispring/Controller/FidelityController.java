package tn.spring.pispring.Controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Fidelity;
import tn.spring.pispring.Interfaces.FidelityInterface;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/fidelity")
public class FidelityController {
    FidelityInterface fidelityInterface;

    @PostMapping("/createfidelity")
    public Fidelity createfidelity(Fidelity fidelity) {
        return fidelityInterface.createfidelity(fidelity);
    }

    @GetMapping("/retrieveAllFidelity")
    public List<Fidelity> retrieveAllFidelity() {
        return fidelityInterface.retrieveAllFidelity();
    }

    @PutMapping("/updatefidelity")
    public Fidelity updateFidelity(@RequestBody Fidelity fidelity) {
        return fidelityInterface.updateFidelity(fidelity);
    }

    @DeleteMapping("/retrieveFidelity/{idFidelite}")
    public Fidelity retrieveFidelity(@PathVariable("idFidelite ")long idFidelite) {
        return fidelityInterface.retrieveFidelity(idFidelite);
    }
}
