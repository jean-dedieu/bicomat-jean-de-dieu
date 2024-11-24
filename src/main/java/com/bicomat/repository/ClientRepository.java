package com.bicomat.repository;

import com.bicomat.model.Client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.util.List;

public class ClientRepository implements Repository<Client> {
    private EntityManager em = Persistence.createEntityManagerFactory("bicomatPU").createEntityManager();

    @Override
    public Client save(Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        return client;
    }

    @Override
    public Client findById(Long id) {
        return em.find(Client.class, id);
    }

    @Override
    public List<Client> findAll() {
        return em.createQuery("FROM Client", Client.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        Client client = findById(id);
        if (client != null) {
            em.remove(client);
        }
        em.getTransaction().commit();
    }
}