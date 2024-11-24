package com.bicomat.service;

import com.bicomat.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction creerTransaction(Long compteId, Double montant, String type);
    List<Transaction> consulterTransactions(Long compteId);
}