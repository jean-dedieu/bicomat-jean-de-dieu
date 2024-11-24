package com.bicomat.service;

import com.bicomat.model.Client;
import com.bicomat.model.Compte;
import com.bicomat.repository.ClientRepository;
import com.bicomat.repository.CompteRepository;

import java.util.List;

/**
 * Implémentation du service pour la gestion des comptes.
 */
public class CompteServiceImpl implements CompteService {
    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;

    public CompteServiceImpl(CompteRepository compteRepository, ClientRepository clientRepository) {
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Compte creerCompte(String type, Double solde, Long clientId) {
        Client client = clientRepository.findById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Client non trouvé !");
        }
        Compte compte = new Compte(type, solde, client);
        return compteRepository.save(compte);
    }

    @Override
    public Compte crediterCompte(Long compteId, Double montant) {
        Compte compte = compteRepository.findById(compteId);
        if (compte == null) {
            throw new IllegalArgumentException("Compte non trouvé !");
        }
        compte.setSolde(compte.getSolde() + montant);
        return compteRepository.save(compte);
    }

    @Override
    public Compte debiterCompte(Long compteId, Double montant) {
        Compte compte = compteRepository.findById(compteId);
        if (compte == null) {
            throw new IllegalArgumentException("Compte non trouvé !");
        }
        if (compte.getSolde() < montant) {
            throw new IllegalArgumentException("Fonds insuffisants !");
        }
        compte.setSolde(compte.getSolde() - montant);
        return compteRepository.save(compte);
    }

    @Override
    public List<Compte> consulterComptesParClient(Long clientId) {
        return compteRepository.findAllByClientId(clientId);
    }
}