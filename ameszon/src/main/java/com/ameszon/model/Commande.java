package com.ameszon.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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
        id = generatedId();
    }

    public Commande( User user ) {
        id = generatedId();
        this.user = user;
    }

    public String generatedId() {
        Random rand = new Random();
        String str = "_";

        for ( int i = 0; i < 7; i++ )
            str += (char) ( rand.nextInt( 26 ) + 65 );

        return date.getDayOfMonth() + "" + date.getMonthValue() + "" + date.getYear() + str;
    }

    @Override
    public String toString() {
        return "Commande [id=" + id + ", date=" + date + "]";
    }
}
