package com.bicomat.console;

import com.bicomat.model.Client;
import com.bicomat.model.Compte;
import com.bicomat.service.ClientService;
import com.bicomat.service.ClientServiceImpl;
import com.bicomat.service.CompteService;
import com.bicomat.service.CompteServiceImpl;
import com.bicomat.service.TransactionService;
import com.bicomat.service.TransactionServiceImpl;
import com.bicomat.repository.ClientRepository;
import com.bicomat.repository.CompteRepository;
import com.bicomat.repository.TransactionRepository;

public class Main {
    public static void main(String[] args) {
        // Initialisation des repositories
        ClientRepository clientRepository = new ClientRepository();
        CompteRepository compteRepository = new CompteRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        // Initialisation des services
        ClientService clientService = new ClientServiceImpl(clientRepository);
        CompteService compteService = new CompteServiceImpl(compteRepository, clientRepository);
        TransactionService transactionService = new TransactionServiceImpl(transactionRepository, compteRepository);

        // Création d'un client
        Client client = clientService.creerClient("Jean Dupont", "jean.dupont@example.com", "motdepasse123");
        System.out.println("Client créé : " + client);

        // Création d'un compte pour le client
        Compte compte = compteService.creerCompte("courant", 1000.0, client.getId());
        System.out.println("Compte créé : " + compte);

        // Ajout d'une transaction (crédit)
        transactionService.creerTransaction(compte.getId(), 500.0, "crédit");
        System.out.println("Transaction de crédit effectuée ! Solde : " + compte.getSolde());

        // Ajout d'une transaction (débit)
        transactionService.creerTransaction(compte.getId(), 200.0, "débit");
        System.out.println("Transaction de débit effectuée ! Solde : " + compte.getSolde());

        // Consultation des transactions
        System.out.println("Transactions du compte : " + transactionService.consulterTransactions(compte.getId()));
    }
}