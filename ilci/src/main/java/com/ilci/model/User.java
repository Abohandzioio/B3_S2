package com.ilci.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer        id;

    @Column( length = 10 )
    private String         sexe;

    @Size( min = 2, max = 30 )
    @Column( length = 30, nullable = false )
    private String         prenom;

    @Size( min = 2, max = 30 )
    @Column( length = 30, nullable = false )
    private String         nom;

    @Size( min = 4, max = 8 )
    @NotBlank
    @Column( length = 10, nullable = false, unique = true )
    private String         login;

    @NotEmpty
    @Column( length = 10, nullable = false )
    private String         mdp;
    private String         ville  = "Paris";
    private String         statut = "ETUDIANT";

    @ManyToOne
    @JoinColumn( name = "promo_id" )
    private Promo          promo;

    @OneToMany( mappedBy = "etudiant" )
    private List<Notation> notes  = new ArrayList<>();

    @Override
    public String toString() {
        return "User [id=" + id + ", sexe=" + sexe + ", prenom=" + prenom + ", nom=" + nom + ", login=" + login
                + ", mdp=" + mdp + ", ville=" + ville + ", statut=" + statut + ", promo=" + promo + "]";
    }

    // @Override
    // public String toString() {
    // return "User [id=" + id + ", sexe=" + sexe + ", prenom=" + prenom + ",
    // nom=" + nom + ", login=" + login
    // + ", mdp=" + mdp + ", statut=" + statut + "]";
    // }
}
