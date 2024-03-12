package com.ilci.UserController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ilci.model.Promo;
import com.ilci.model.User;
import com.ilci.repository.PromoRepository;
import com.ilci.repository.UserRepository;

@Controller
public class PromoController {

    @Autowired
    PromoRepository promoRepository;

    @Autowired
    UserRepository userRepository;
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
    public String delete( @PathVariable int id, RedirectAttributes ra ) {
        Promo promo = promoRepository.findById( id ).get();

        try {
            promoRepository.delete( promo );
            ra.addFlashAttribute( "success", "La promo " + promo.getNom() + " supprimée avec success" );
        } catch ( Exception e ) {
            ra.addFlashAttribute( "warning", "La promo " + promo.getNom() + " contient des users et/ou matières" );
        }
        return "redirect:/promos";
    }
    
    @GetMapping("/promos/etudiant/{id}")
    public String etudiantPromo(@PathVariable int id, Model model) {
    	Promo promo = promoRepository.findById(id).get();
        List<User> listetudiant = userRepository.findByPromo(promo);
        model.addAttribute("etudiants" , listetudiant);
        model.addAttribute( "promo_id" , id);
        
    			return "promo/etudiant";
    }
}
