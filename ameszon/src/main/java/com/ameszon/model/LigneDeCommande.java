package com.ameszon.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LigneDeCommande {

    @EmbeddedId
    ArticleCommandeKey id;

    @ManyToOne
    @MapsId( "articleId" )
    @JoinColumn( name = "id_article" )
    private Article    article;

    @ManyToOne
    @MapsId( "commandeId" )
    @JoinColumn( name = "id_commande" )
    private Commande   commande;

    private int        quantity;

    private double     prix;

    public LigneDeCommande() {
    }

    @Override
    public String toString() {
        return "LigneDeCommande [id=" + id + ", article=" + article + ", commande=" + commande + ", quantity="
                + quantity + ", prix=" + prix + "]";
    }

    public LigneDeCommande( ArticleCommandeKey id, Article article, Commande commande, int quantity, double prix ) {
        this.id = id;
        this.article = article;
        this.commande = commande;
        this.quantity = quantity;
        this.prix = prix;
    }

}
