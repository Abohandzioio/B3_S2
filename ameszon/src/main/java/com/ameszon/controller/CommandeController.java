package com.ameszon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ameszon.model.ArticlePanier;
import com.ameszon.repository.ArtPanierRepository;
import com.ameszon.repository.PanierRepository;

@Controller
@RequestMapping( "/commande" )
public class CommandeController {

    @Autowired
    ArtPanierRepository artPanierRepository;
    @Autowired
    PanierRepository    panierRepository;

    @GetMapping( "/valider/{id}" )
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
