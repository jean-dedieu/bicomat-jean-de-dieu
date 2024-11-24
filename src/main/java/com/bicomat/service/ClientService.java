package com.bicomat.service;

import com.bicomat.model.Client;

public interface ClientService {
    Client creerClient(String nom, String email, String motDePasse);
    Client trouverClientParId(Long id);
    Client trouverClientParEmail(String email);
}
