package com.bicomat.service;

import com.bicomat.model.Compte;
import com.bicomat.model.Transaction;

import java.util.List;

public interface CompteService {
    Compte creerCompte(String type, Double solde, Long clientId);
    Compte crediterCompte(Long compteId, Double montant);
    Compte debiterCompte(Long compteId, Double montant);
    List<Transaction> consulterTransactions(Long compteId);
}