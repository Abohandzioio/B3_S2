package com.ameszon.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Panier {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer             id;

    @OneToOne
    @JoinColumn( name = "id_client", referencedColumnName = "id" )
    private User                user;

    @OneToMany( mappedBy = "panier" )
    private List<ArticlePanier> paniers = new ArrayList<>();

    @Override
    public String toString() {
        return "Panier [id=" + id;
    }

}
