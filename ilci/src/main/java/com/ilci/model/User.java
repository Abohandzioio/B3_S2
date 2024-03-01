package com.ilci.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String  sexe;
    private String  prenom;
    private String  nom;
    private String  login;
    private String  mdp;
    private String  ville  = "Paris";
    private String  statut = "ETUDIANT";

    @ManyToOne
    @JoinColumn( name = "promo" )
    private Promo   promo;

    @Override
    public String toString() {
        return "User [id=" + id + ", sexe=" + sexe + ", prenom=" + prenom + ", nom=" + nom + ", login=" + login
                + ", mdp=" + mdp + ", statut=" + statut + "]";
    }
}
