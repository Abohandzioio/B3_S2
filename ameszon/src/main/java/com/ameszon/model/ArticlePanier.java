package com.ameszon.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ArticlePanier {

    @EmbeddedId
    private ArticlePanierKey id;

    @Size( min = 0 )
    private int              quantity = 1;
    private double           prix;

    @ManyToOne
    @MapsId( "articleId" )
    @JoinColumn( name = "id_article" )
    private Article          article;

    @ManyToOne
    @MapsId( "panierId" )
    @JoinColumn( name = "id_panier" )
    private Panier           panier;

    @Override
    public String toString() {
        return "ArticlePanier [id=" + id + ", quantity=" + quantity + ", prix=" + prix + "]";
    }

}
