package com.ameszon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ameszon.model.Article;
import com.ameszon.model.ArticlePanier;
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

    final String     UPLOADDIR = "src\\main\\resources\\static\\img\\";

    @PostMapping
    public String insert(
            @Valid Article article,
            BindingResult result,
            RedirectAttributes ra,
            Model model,
            @RequestParam( "photo" ) MultipartFile photo ) throws IOException {

        if ( result.hasErrors() ) {
            model.addAttribute( "categories", categorieService.getAllCategories() );
            return "article/new";
        }

        // NOM PHOTO UPLOADE
        String fileName = StringUtils.cleanPath( photo.getOriginalFilename() );

        // DOSSIER DESTINATION
        Path uploadPath = Paths.get( UPLOADDIR + article.getLibelle() + "\\" );

        // TEST SI LE DOSSIER DE DOSTINATION N'EXISTE PAS
        if ( !Files.exists( uploadPath ) ) {
            Files.createDirectories( uploadPath );
        }

        // COPIE DE LA PHOTO DANS SON REPERTOIRE
        InputStream inputStream = photo.getInputStream();
        Path filePath = uploadPath.resolve( fileName );
        Files.copy( inputStream, filePath, StandardCopyOption.REPLACE_EXISTING );

        article.setImage( fileName );

        articleService.createArticle( article );
        ra.addFlashAttribute( "success", "Article créé avec succès ! " );

        return "redirect:/article/admin";
    }

    @GetMapping( "/admin" )
    public String index( Model model ) {
        model.addAttribute( "articles", articleService.getAllArticle() );

        return "article/index";
    }

    @GetMapping( "/detail/{id}" )
    public String detail( Model model, @PathVariable int id ) {

        model.addAttribute( "article", articleService.getArticleById( id ) );
        model.addAttribute( "artPanier", new ArticlePanier() );

        return "article/detail";
    }

    @GetMapping( "/new/admin" )
    public String create( Model model ) {
        model.addAttribute( "article", new Article() );
        model.addAttribute( "categories", categorieService.getAllCategories() );

        return "article/new";
    }

    @GetMapping( "/update/admin/{id}" )
    public String update( @PathVariable int id, Model model, RedirectAttributes ra ) {
        if ( articleService.getArticleById( id ) == null ) {
            ra.addFlashAttribute( "warning", "Cet article n'existe pas ! " );
            return "redirect:/article/admin";
        }

        model.addAttribute( "article", articleService.getArticleById( id ) );
        model.addAttribute( "categories", categorieService.getAllCategories() );

        return "article/new";
    }

    @GetMapping( "/delete/admin/{id}" )
    public String delete( @PathVariable int id, RedirectAttributes ra ) {
        if ( articleService.getArticleById( id ) == null ) {
            ra.addFlashAttribute( "warning", "Cet article n'existe pas ! " );
            return "redirect:/article";
        }

        articleService.deleteArticleById( id );
        ra.addFlashAttribute( "success", "Article supprimé avec succès ! " );

        return "redirect:/article/admin";
    }
}
