package com.ameszon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ameszon.model.ArticlePanier;
import com.ameszon.model.ArticlePanierKey;
import com.ameszon.model.User;
import com.ameszon.repository.ArtPanierRepository;
import com.ameszon.repository.PanierRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping( "/panier" )
public class PanierController {

    @Autowired
    ArtPanierRepository artPanierRepository;
    @Autowired
    PanierRepository    panierRepository;

    @PostMapping
    public String addToPanier( @Valid ArticlePanier artPanier,
            BindingResult result,
            Model model,
            HttpSession session,
            RedirectAttributes ra ) {

        if ( result.hasErrors() || ( artPanier.getQuantity() > artPanier.getArticle().getStock() ) ) {
            model.addAttribute( "article", artPanier.getArticle() );
            model.addAttribute( "artPanier", artPanier );

            return "article/detail";
        }

        // TEST CONNEXION
        if ( session.getAttribute( "user" ) == null ) {
            ra.addFlashAttribute( "warning", "veuillez vous connecter" );
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

    @GetMapping( "/show/{id}" )
    public String showPanier( @PathVariable Integer id, Model model ) {
        model.addAttribute( "artsPanier", artPanierRepository.findAllByPanierId( id ) );
        model.addAttribute( "total", total( artPanierRepository.findAllByPanierId( id ) ) );

        return "panier/index";
    }

    private double total( List<ArticlePanier> artpaniers ) {
        double total = 0.0;
        for ( ArticlePanier artP : artpaniers ) {
            total += artP.getPrix() * artP.getQuantity();
        }
        return total;
    }

}
