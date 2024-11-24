package com.bicomat.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe représentant une Transaction bancaire.
 * Une transaction est liée à un compte et représente un débit ou un crédit.
 */
@Entity
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de la transaction

    @Column(nullable = false)
    private Double montant; // Montant de la transaction

    @Column(nullable = false)
    private String type; // Type de transaction : 'crédit', 'débit'

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now(); // Date de la transaction

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private Compte compte; // Compte associé à cette transaction

    /**
     * Constructeur personnalisé.
     *
     * @param montant Montant de la transaction.
     * @param type    Type de transaction : 'crédit' ou 'débit'.
     * @param compte  Compte associé à la transaction.
     */
    public Transaction(Double montant, String type, Compte compte) {
        this.montant = montant;
        this.type = type;
        this.compte = compte;
    }
}