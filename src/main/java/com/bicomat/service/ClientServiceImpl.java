package com.bicomat.service;

import com.bicomat.model.Client;
import com.bicomat.repository.ClientRepository;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client creerClient(String nom, String email, String motDePasse) {
        Client client = new Client(nom, email, motDePasse);
        return clientRepository.save(client);
    }

    @Override
    public Client trouverClientParId(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client trouverClientParEmail(String email) {
        return clientRepository.findAll().stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}