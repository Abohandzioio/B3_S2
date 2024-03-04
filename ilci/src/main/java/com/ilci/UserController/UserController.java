package com.ilci.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ilci.model.User;
import com.ilci.repository.PromoRepository;
import com.ilci.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserRepository  userRepository;
    @Autowired
    PromoRepository promoRepository;

    @GetMapping( "/user" )
    public String index( Model model ) {
        model.addAttribute( "users", userRepository.findAll() );

        return "user/index";
    }

    @GetMapping( "/user/login" )
    public String login() {
        return "user/login";
    }

    @PostMapping( "/user/login/try" )
    public String login(
            @RequestParam String login,
            @RequestParam String mdp,
            HttpSession session ) {

        User u = userRepository.findByLoginAndMdp( login, mdp );

        if ( u != null ) {
            session.setAttribute( "user", u );
            return "redirect:/user";
        }

        return "user/login";
    }

    @GetMapping( "/user/logout" )
    public String logout( HttpSession session ) {
        session.invalidate();

        return "redirect:/user";
    }

    @GetMapping( "/user/add" )
    public String ajouter( Model model ) {
        User user = new User();
        model.addAttribute( "user", user );
        model.addAttribute( "promos", promoRepository.findAll() );

        return "user/formulaire";
    }

    @PostMapping( "/user/insert" )
    public String insert( @Valid User user, BindingResult result, Model model ) {

        if ( result.hasErrors() ) {
            model.addAttribute( "promos", promoRepository.findAll() );
            return "user/formulaire";
        }

        if ( userRepository.findUserByLogin( user.getLogin() ) != null ) {
            model.addAttribute( "unique", "Ce login existe déjà! " );
            model.addAttribute( "promos", promoRepository.findAll() );
            return "user/formulaire";
        }

        userRepository.save( user );
        return "redirect:/user";
    }

    @GetMapping( "/user/user/{id}" )
    public String user( @PathVariable int id, Model model ) {
        User user = userRepository.findById( id ).get();

        model.addAttribute( "user", user );

        return "user/user";
    }

    @GetMapping( "/user/update/{id}" )
    public String update( @PathVariable int id, Model model ) {
        User user = userRepository.findById( id ).get();

        model.addAttribute( "user", user );

        return "user/formulaire";
    }

    @GetMapping( "/user/delete/{id}" )
    public String delete( @PathVariable int id ) {
        User user = userRepository.findById( id ).get();
        userRepository.delete( user );
        return "redirect:/user";
    }

}
