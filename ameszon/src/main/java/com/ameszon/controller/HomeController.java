package com.ameszon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ameszon.model.User;
import com.ameszon.repository.PanierRepository;
import com.ameszon.repository.UserRepository;
import com.ameszon.services.ArticleService;
import com.ameszon.services.PanierService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    ArticleService   articleService;
    @Autowired
    UserRepository   userRepository;
    @Autowired
    PanierRepository panierRepository;
    @Autowired
    PanierService    panierService;

    @GetMapping
    public String index( Model model, HttpSession session ) {

        model.addAttribute( "articles", articleService.getAllArticle() );

        // à déplacer dans usercontroller
        User u = userRepository.findByLoginAndMdp( "ilci3", "ilci" );
        session.setAttribute( "user", u );
        session.setAttribute( "totalArt", panierService.totalPanier( u ) );

        return "index";
    }

}
