package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.Fidelity;

import java.util.List;

public interface FidelityInterface {
    public Fidelity createfidelity(Fidelity fidelity);

    List<Fidelity> retrieveAllFidelity();

    Fidelity updateFidelity (Fidelity fidelity);

    Fidelity retrieveFidelity (long idFidelite);
}
