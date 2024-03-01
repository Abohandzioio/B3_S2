package com.ilci.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Promo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer       id;
    private String        nom;

    @OneToMany( mappedBy = "promo" )
    private List<User>    users    = new ArrayList<>();

    @OneToMany( mappedBy = "promo" )
    private List<Matiere> matieres = new ArrayList<>();

    @Override
    public String toString() {
        return "Promo [id=" + id + ", nom=" + nom + "]";
    }

}
