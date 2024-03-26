package com.ameszon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ameszon.model.ArticlePanier;
import com.ameszon.model.User;
import com.ameszon.repository.PanierRepository;
import com.ameszon.repository.UserRepository;
import com.ameszon.services.ArticleService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    ArticleService   articleService;
    @Autowired
    UserRepository   userRepository;
    @Autowired
    PanierRepository panierRepository;

    @GetMapping
    public String index( Model model, HttpSession session ) {
        model.addAttribute( "articles", articleService.getAllArticle() );

        // à déplacer dans usercontroller
        User u = userRepository.findByLoginAndMdp( "ilci3", "ilci" );
        session.setAttribute( "user", u );
        session.setAttribute( "totalArt", totalPanier( u ) );

        return "index";
    }

    private int totalPanier( User u ) {

        int nbArt = 0;

        if ( panierRepository.findByUser( u ) != null )
            for ( ArticlePanier ap : panierRepository.findByUser( u ).getPaniers() )
                nbArt += ap.getQuantity();

        return nbArt;
    }
    //
    // @GetMapping( "/new" )
    // public String create( Model model ) {
    // model.addAttribute( "article", new Article() );
    // model.addAttribute( "categories", categorieService.getAllCategories() );
    //
    // return "article/new";
    // }
    //
    // @PostMapping
    // public String insert( @Valid Article article, BindingResult result,
    // RedirectAttributes ra, Model model ) {
    //
    // if ( result.hasErrors() ) {
    // model.addAttribute( "categories", categorieService.getAllCategories() );
    // return "article/new";
    // }
    //
    // articleService.createArticle( article );
    // ra.addFlashAttribute( "success", "Article créé avec succès ! " );
    //
    // return "redirect:/article";
    // }
    //
    // @GetMapping( "/update/{id}" )
    // public String update( @PathVariable int id, Model model,
    // RedirectAttributes ra ) {
    // if ( articleService.getArticleById( id ) == null ) {
    // ra.addFlashAttribute( "warning", "Cet article n'existe pas ! " );
    // return "redirect:/article";
    // }
    //
    // model.addAttribute( "article", articleService.getArticleById( id ) );
    // model.addAttribute( "categories", categorieService.getAllCategories() );
    //
    // return "article/new";
    // }
    //
    // @GetMapping( "/delete/{id}" )
    // public String delete( @PathVariable int id, RedirectAttributes ra ) {
    // if ( articleService.getArticleById( id ) == null ) {
    // ra.addFlashAttribute( "warning", "Cet article n'existe pas ! " );
    // return "redirect:/article";
    // }
    //
    // articleService.deleteArticleById( id );
    // ra.addFlashAttribute( "success", "Article supprimé avec succès ! " );
    //
    // return "redirect:/article";
    // }
}
