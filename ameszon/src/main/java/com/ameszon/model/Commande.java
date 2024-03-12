package com.ameszon.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Commande {

    @Id
    private String                id;

    private LocalDate             date = LocalDate.now();

    @ManyToOne
    @JoinColumn( name = "id_client" )
    private User                  user;

    @OneToOne( mappedBy = "commande" )
    private Facture               facture;

    @OneToMany( mappedBy = "commande" )
    private List<LigneDeCommande> ldc;

    public Commande() {

    }

    @Override
    public String toString() {
        return "Commande [id=" + id + ", date=" + date + ", facture=" + facture + ", ldc=" + ldc + "]";
    }
}
