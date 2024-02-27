package com.intro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intro.model.User;

@Controller
public class HomeController {

    private List<User> users = new ArrayList<>();

    @GetMapping( "/" )
    public String index( Model model ) {

        model.addAttribute( "users", users );

        return "index";
    }

    @PostMapping( "/add/insert" )
    public String addInsert( @RequestParam Map<String, String> args ) {
        User u = new User( users.size() + 1, args.get( "prenom" ), args.get( "nom" ),
                Integer.parseInt( args.get( "age" ) ) );
        users.add( u );

        return "redirect:/";
    }

    // @PostMapping( "/add/insert" )
    // public String addInsert( @RequestParam String prenom ) {
    // prenoms.add( prenom );
    //
    // return "redirect:/";
    // }

    @GetMapping( "/add" )
    public String add() {
        System.out.println( "add" );
        return "user/add";
    }

    @GetMapping( "/delete/{id}" )
    public String delete( @PathVariable int id ) {
        System.out.println( id );
        return "redirect:/";
    }

}
