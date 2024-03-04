package com.ilci.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ilci.model.Promo;
import com.ilci.repository.PromoRepository;

@Controller
public class PromoController {

    @Autowired
    PromoRepository promoRepository;

    @GetMapping( "/promos" )
    public String index( Model model ) {
        model.addAttribute( "promo", new Promo() );
        model.addAttribute( "promos", promoRepository.findAll() );
        return "promo/index";
    }

    @PostMapping( "/promos/add" )
    public String ajouter( @ModelAttribute Promo promo ) {
        promoRepository.save( promo );
        return "redirect:/promos";
    }

    @GetMapping( "/promos/{id}" )
    public String delete( Model model, @PathVariable int id ) {
        Promo promo = promoRepository.findById( id ).get();

        if ( promo.getMatieres().size() != 0 || promo.getUsers().size() != 0 ) {
            return "redirect:/promos";
        }

        promoRepository.delete( promo );
        return "redirect:/promos";
    }
}
