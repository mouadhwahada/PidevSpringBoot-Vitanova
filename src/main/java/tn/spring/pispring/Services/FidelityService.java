package tn.spring.pispring.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Fidelity;
import tn.spring.pispring.Interfaces.FidelityInterface;
import tn.spring.pispring.Repositories.FideliteRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class FidelityService implements FidelityInterface {
    FideliteRepo fideliteRepo;

    @Override
    public List<Fidelity> retrieveAllFidelity() {
        return fideliteRepo.findAll();
    }


    @Override
    public Fidelity updateFidelity(Fidelity fidelity) {
        return fideliteRepo.save(fidelity);
    }

    @Override
    public Fidelity retrieveFidelity(long idFidelite) {
        return fideliteRepo.findById(idFidelite).orElse(null);
    }

    @Override
    public Fidelity createfidelity(Fidelity fidelity) {
        return fideliteRepo.save(fidelity);
    }
}





