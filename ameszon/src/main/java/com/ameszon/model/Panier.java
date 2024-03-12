package com.ameszon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Panier {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @OneToOne
    @JoinColumn( name = "id_client", referencedColumnName = "id" )
    private User    user;

    @Override
    public String toString() {
        return "Panier [id=" + id + "]";
    }

}
