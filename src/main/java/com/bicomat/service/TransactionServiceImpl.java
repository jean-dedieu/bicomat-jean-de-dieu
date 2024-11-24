package com.bicomat.service;

import com.bicomat.model.Compte;
import com.bicomat.model.Transaction;
import com.bicomat.repository.CompteRepository;
import com.bicomat.repository.TransactionRepository;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CompteRepository compteRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CompteRepository compteRepository) {
        this.transactionRepository = transactionRepository;
        this.compteRepository = compteRepository;
    }

    @Override
    public Transaction creerTransaction(Long compteId, Double montant, String type) {
        Compte compte = compteRepository.findById(compteId);
        if (compte == null) {
            throw new IllegalArgumentException("Compte non trouvé !");
        }

        if (type.equalsIgnoreCase("débit") && compte.getSolde() < montant) {
            throw new IllegalArgumentException("Fonds insuffisants !");
        }

        // Met à jour le solde du compte
        if (type.equalsIgnoreCase("crédit")) {
            compte.setSolde(compte.getSolde() + montant);
        } else if (type.equalsIgnoreCase("débit")) {
            compte.setSolde(compte.getSolde() - montant);
        }
        compteRepository.save(compte);

        // Enregistre la transaction
        Transaction transaction = new Transaction(montant, type, compte);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> consulterTransactions(Long compteId) {
        Compte compte = compteRepository.findById(compteId);
        if (compte == null) {
            throw new IllegalArgumentException("Compte non trouvé !");
        }
        return compte.getTransactions();
    }
}