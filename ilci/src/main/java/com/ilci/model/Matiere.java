package com.ilci.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Matiere {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String  libelle;

    @ManyToOne
    @JoinColumn( name = "promo" )
    private Promo   promo;

}
