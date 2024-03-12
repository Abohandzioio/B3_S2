package com.ameszon.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Categorie {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer       id;

    @Column( length = 20, unique = true )
    private String        nom;

    @OneToMany( mappedBy = "categorie" )
    private List<Article> articles;

    @Override
    public String toString() {
        return "Categorie [id=" + id + ", nom=" + nom + ", articles=" + articles + "]";
    }

}
