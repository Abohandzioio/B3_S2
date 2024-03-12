package com.ameszon.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer        id;

    @Column( length = 10 )
    private String         sexe;

    @Column( length = 30 )
    private String         prenom;

    @Column( length = 30 )
    private String         nom;

    @Column( length = 10, unique = true )
    private String         login;

    @Column( length = 8 )
    private String         mdp;

    @Column( length = 30 )
    private String         adresse;

    @Column( length = 5 )
    private int            cp;

    @Column( length = 30 )
    private String         Ville;

    @Column( length = 20 )
    private String         tel;

    @Column( length = 10 )
    private String         statut;

    @Column( length = 30 )
    private String         email;

    @OneToMany( mappedBy = "user" )
    private List<Commande> commandes;

    @OneToMany( mappedBy = "user" )
    private List<Facture>  factures;

    @OneToOne( mappedBy = "user" )
    private Panier         panier;

    @Override
    public String toString() {
        return "User [id=" + id + ", sexe=" + sexe + ", prenom=" + prenom + ", nom=" + nom + ", login=" + login
                + ", mdp=" + mdp + ", adresse=" + adresse + ", cp=" + cp + ", Ville=" + Ville + ", tel=" + tel
                + ", statut=" + statut + ", email=" + email + ", commandes=" + commandes + ", factures=" + factures
                + ", panier=" + panier + "]";
    }

}
