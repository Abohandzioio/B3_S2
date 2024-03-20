package com.ameszon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ameszon.model.ArticlePanier;
import com.ameszon.model.ArticlePanierKey;
import com.ameszon.model.User;
import com.ameszon.repository.ArtPanierRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping( "/panier" )
public class PanierController {

    @Autowired
    ArtPanierRepository artPanierRepository;

    @PostMapping
    public String addToPanier( @Valid ArticlePanier artPanier,
            BindingResult result,
            Model model,
            HttpSession session ) {

        if ( result.hasErrors() || ( artPanier.getQuantity() > artPanier.getArticle().getStock() ) ) {
            model.addAttribute( "article", artPanier.getArticle() );
            model.addAttribute( "artPanier", artPanier );

            return "article/detail";
        }

        // TEST CONNEXION
        if ( session.getAttribute( "user" ) == null ) {
            // msg flash
            return "redirect:/user/login";
        }

        User user = (User) session.getAttribute( "user" );
        artPanier.setPanier( user.getPanier() );

        // clé primaire
        ArticlePanierKey artPanKey = new ArticlePanierKey( artPanier.getArticle().getId(), user.getPanier().getId() );
        artPanier.setId( artPanKey );

        artPanierRepository.save( artPanier );

        return "redirect:/";
    }

}
