package com.intro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private List<String> prenoms = new ArrayList<>();

    @GetMapping( "/" )
    public String index( Model model ) {

        model.addAttribute( "type", "Liste de prénom" );
        model.addAttribute( "prenoms", prenoms );

        return "index";
    }

    @PostMapping( "/add/insert" )
    public String addInsert( @RequestParam String prenom ) {
        prenoms.add( prenom );

        return "redirect:/";
    }

    @GetMapping( "/add" )
    public String add() {
        System.out.println( "add" );
        return "user/add";
    }

}
