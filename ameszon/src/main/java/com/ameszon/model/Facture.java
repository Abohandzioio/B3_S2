package com.ameszon.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Facture {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer   id;

    @Column( length = 30 )
    private String    adresse;

    @Column( length = 5 )
    private int       cp;

    @Column( length = 30 )
    private String    Ville;

    private LocalDate date = LocalDate.now();

    @ManyToOne
    @JoinColumn( name = "id_client" )
    private User      user;

    @OneToOne
    @JoinColumn( name = "id_commande", referencedColumnName = "id" )
    private Commande  commande;

    @Override
    public String toString() {
        return "Facture [id=" + id + ", adresse=" + adresse + ", cp=" + cp + ", Ville=" + Ville + ", date=" + date
                + "]";
    }

}
