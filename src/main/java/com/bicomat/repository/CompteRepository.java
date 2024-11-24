package com.bicomat.repository;

import com.bicomat.model.Compte;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 * Repository pour gérer les opérations sur les comptes.
 */
public class CompteRepository implements Repository<Compte> {

    private EntityManager em;

    public CompteRepository() {
        this.em = Persistence.createEntityManagerFactory("bicomatPU").createEntityManager();
    }

    @Override
    public Compte save(Compte compte) {
        em.getTransaction().begin();
        if (compte.getId() == null) {
            em.persist(compte); // Enregistre un nouveau compte
        } else {
            em.merge(compte); // Met à jour un compte existant
        }
        em.getTransaction().commit();
        return compte;
    }

    @Override
    public Compte findById(Long id) {
        return em.find(Compte.class, id);
    }

    @Override
    public List<Compte> findAll() {
        return em.createQuery("FROM Compte", Compte.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        Compte compte = findById(id);
        if (compte != null) {
            em.remove(compte);
        }
        em.getTransaction().commit();
    }
}