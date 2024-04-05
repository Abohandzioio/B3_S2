package com.ameszon.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer               id;

    @Column( length = 20 )
    @Size( min = 2, max = 20 )
    @NotNull
    private String                libelle;

    @Min( 1 )
    private double                prix;

    @Min( 0 )
    private int                   stock;

    @Column( length = 20 )
    @Size( min = 2, max = 20 )
    private String                marque;

    @Column( length = 20 )
    @Size( min = 2, max = 20 )
    private String                type;

    @Column( length = 30 )
    @Size( min = 7, max = 30 )
    private String                image;

    @Size( min = 60, max = 700 )
    private String                description;

    @ManyToOne
    @JoinColumn( name = "id_categorie" )
    private Categorie             categorie;

    @OneToMany( mappedBy = "article" )
    private List<LigneDeCommande> ldc;

    @OneToMany( mappedBy = "article" )
    private List<ArticlePanier>   paniers;

    @OneToMany( mappedBy = "article" )
    private List<Images>          images;

    public String getLogo() {
        return "/img/" + this.libelle + "/" + this.image;
    }

    public Article() {
        this.description = """
                Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Nisi laudantium quam omnis blanditiis minus temporibus non dolorum
                fugit modi est ea saepe numquam quis repudiandae animi sint aspernatur porro
                et quae maxime earum labore illum eos sit dignissimos neque tempore nobis ab
                quod quos voluptatum vel quia architecto. Provident officiis beatae minus assumenda
                eveniet deleniti dolor eius iste? Amet praesentium at quia rerum fugit error.
                """;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", libelle=" + libelle + ", prix=" + prix + ", stock=" + stock + ", marque="
                + marque + ", type=" + type + ", description=" + description + "]";
    }

}
