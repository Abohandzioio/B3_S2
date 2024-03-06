package com.ilci.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ilci.model.Matiere;
import com.ilci.repository.MatiereRepository;
import com.ilci.repository.PromoRepository;

import jakarta.validation.Valid;

@Controller
public class MatiereController {

    @Autowired
    PromoRepository   promoRepository;

    @Autowired
    MatiereRepository matiereRepository;

    @GetMapping( "/matieres" )
    public String index( Model model ) {
        model.addAttribute( "matieres", matiereRepository.findAll() );
        return "matieres/index";
    }

    @GetMapping( "/matieres/add" )
    public String add( Model model ) {
        model.addAttribute( "matiere", new Matiere() );
        model.addAttribute( "promos", promoRepository.findAll() );
        return "matieres/new";
    }

    @PostMapping( "/matieres/create" )
    public String create( @Valid Matiere matiere, BindingResult result, Model model, RedirectAttributes ra ) {

        if ( result.hasErrors() ) {
            model.addAttribute( "promos", promoRepository.findAll() );
            return "matieres/new";
        }

        try {
            matiereRepository.save( matiere );
            ra.addFlashAttribute( "success", "La matirèr " + matiere.getLibelle() + " est créée" );
        } catch ( Exception e ) {
            model.addAttribute( "warning", "La matirèr " + matiere.getLibelle() + " existe déjà" );
            model.addAttribute( "promos", promoRepository.findAll() );
            return "matieres/new";
        }

        return "redirect:/matieres";
    }

    @GetMapping( "/matieres/update/{id}" )
    public String update( Model model, @PathVariable int id ) {
        Matiere matiere = matiereRepository.findById( id ).get();
        model.addAttribute( "matiere", matiere );
        model.addAttribute( "promos", promoRepository.findAll() );

        return "matieres/new";
    }

    @GetMapping( "/matieres/delete/{id}" )
    public String delete( @PathVariable int id ) {
        Matiere matiere = matiereRepository.findById( id ).get();
        matiereRepository.delete( matiere );

        return "redirect:/matieres";
    }

}
