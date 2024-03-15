package com.ameszon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ameszon.model.Article;
import com.ameszon.services.ArticleService;
import com.ameszon.services.CategorieService;

import jakarta.validation.Valid;

@Controller
@RequestMapping( "/article" )
public class ArticleController {

    @Autowired
    ArticleService   articleService;

    @Autowired
    CategorieService categorieService;

    @GetMapping
    public String index( Model model ) {
        model.addAttribute( "articles", articleService.getAllArticle() );

        return "article/index";
    }

    @GetMapping( "/new" )
    public String create( Model model ) {
        model.addAttribute( "article", new Article() );
        model.addAttribute( "categories", categorieService.getAllCategories() );

        return "article/new";
    }

    @PostMapping
    public String insert( @Valid Article article, BindingResult result, RedirectAttributes ra, Model model ) {

        if ( result.hasErrors() ) {
            model.addAttribute( "categories", categorieService.getAllCategories() );
            return "article/new";
        }

        articleService.createArticle( article );
        ra.addFlashAttribute( "success", "Article créé avec succès ! " );

        return "redirect:/article";
    }

    @GetMapping( "/update/{id}" )
    public String update( @PathVariable int id, Model model, RedirectAttributes ra ) {
        if ( articleService.getArticleById( id ) == null ) {
            ra.addFlashAttribute( "warning", "Cet article n'existe pas ! " );
            return "redirect:/article";
        }

        model.addAttribute( "article", articleService.getArticleById( id ) );
        model.addAttribute( "categories", categorieService.getAllCategories() );

        return "article/new";
    }

    @GetMapping( "/delete/{id}" )
    public String delete( @PathVariable int id, RedirectAttributes ra ) {
        if ( articleService.getArticleById( id ) == null ) {
            ra.addFlashAttribute( "warning", "Cet article n'existe pas ! " );
            return "redirect:/article";
        }

        articleService.deleteArticleById( id );
        ra.addFlashAttribute( "success", "Article supprimé avec succès ! " );

        return "redirect:/article";
    }
}
