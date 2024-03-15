package com.ameszon.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ameszon.model.Categorie;
import com.ameszon.services.CategorieService;

import jakarta.validation.Valid;

@Controller
@RequestMapping( "/categorie" )
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @GetMapping
    public String index( Model model ) {
        model.addAttribute( "categories", categorieService.getAllCategories() );

        return "categorie/index";
    }

    @GetMapping( "/new" )
    public String newCateg( Model model ) {
        model.addAttribute( "categorie", new Categorie() );

        return "categorie/new";
    }

    @PostMapping
    public String create( @Valid Categorie categorie, BindingResult result, RedirectAttributes ra ) {

        if ( result.hasErrors() ) {
            return "categorie/new";
        }

        if ( categorie.getId() != null ) {
            categorieService.updateCategorie( categorie.getId(), categorie );
            ra.addFlashAttribute( "success", "La ctégorie est modifiée avec succès" );
        } else {
            categorieService.createCat( categorie );
            ra.addFlashAttribute( "success", "La ctégorie est ajoutée avec succès" );
        }

        return "redirect:/categorie";
    }

    @GetMapping( "/show/{id}" )
    public String show( Model model, @PathVariable int id, RedirectAttributes ra ) {
        Optional<Categorie> cat = categorieService.getCategorieById( id );

        if ( cat.isPresent() ) {
            model.addAttribute( "categorie", cat.get() );

            return "categorie/show";
        }

        ra.addFlashAttribute( "warning", "La catégorie demandée n'exixte pas!" );

        return "redirect:/categorie";
    }

    @GetMapping( "/update/{id}" )
    public String update( Model model, @PathVariable int id, RedirectAttributes ra ) {

        Optional<Categorie> cat = categorieService.getCategorieById( id );

        if ( cat.isPresent() ) {
            model.addAttribute( "categorie", cat.get() );
            return "categorie/new";
        }

        return "redirect:/categorie";
    }

    @GetMapping( "/delete/{id}" )
    public String delete( @PathVariable int id, RedirectAttributes ra ) {

        // A COMPLETER

        try {
            categorieService.deleteCatById( id );
        } catch ( Exception e ) {
            ra.addFlashAttribute( "warning", "Cette catégorie ne peut pas être supprimée!" );
        }

        return "redirect:/categorie";
    }

}
