package com.bicomat.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Classe représentant un Compte bancaire.
 * Un compte est associé à un client et peut avoir plusieurs transactions.
 */
@Entity
@Data
@NoArgsConstructor
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique du compte

    @Column(nullable = false)
    private String type; // Type de compte : 'courant', 'épargne'

    @Column(nullable = false)
    private Double solde; // Solde actuel du compte

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; // Client propriétaire du compte

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions; // Liste des transactions associées au compte

    /**
     * Constructeur personnalisé.
     *
     * @param type  Type du compte.
     * @param solde Solde initial du compte.
     * @param client Client propriétaire du compte.
     */
    public Compte(String type, Double solde, Client client) {
        this.type = type;
        this.solde = solde;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}