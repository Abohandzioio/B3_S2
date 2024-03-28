package com.ameszon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ameszon.model.Article;
import com.ameszon.model.ArticlePanier;
import com.ameszon.model.ArticlePanierKey;
import com.ameszon.model.Panier;
import com.ameszon.model.User;
import com.ameszon.repository.ArtPanierRepository;
import com.ameszon.repository.ArticleRepository;
import com.ameszon.repository.PanierRepository;
import com.ameszon.services.PanierService;

import jakarta.servlet.http.HttpSession;

@RestController
public class PanierRequestController {

    @Autowired
    ArtPanierRepository artPanierRepository;
    @Autowired
    PanierRepository    panierRepository;
    @Autowired
    ArticleRepository   articleRepository;
    @Autowired
    PanierService       panierService;

    @PostMapping( "/panier/update/{id_panier}/{id_article}/{qtt}" )
    public Panier update(
            @PathVariable int id_panier,
            @PathVariable int id_article,
            @PathVariable int qtt,
            HttpSession session ) {

        Panier panier = panierRepository.findById( id_panier ).get();
        Article art = articleRepository.findById( id_article ).get();

        // clé primaire article panier
        ArticlePanierKey artPanierKey = new ArticlePanierKey( art.getId(), panier.getId() );

        ArticlePanier articlePanier = artPanierRepository.findById( artPanierKey ).get();

        articlePanier.setQuantity( qtt );
        artPanierRepository.save( articlePanier );

        // MAJ qtt panier dans la session pour affichage
        int nbArtPanier = panierService.totalPanier( (User) session.getAttribute( "user" ) );
        session.setAttribute( "totalArt", nbArtPanier );

        return panier;
    }
}
