package com.ameszon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ameszon.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping( "/user" )
public class UserController {

    @GetMapping( "/logon" )
    public String logon( Model model ) {
        model.addAttribute( "user", new User() );

        return "user/new";
    }

    @GetMapping( "/login" )
    public String login() {
        return "user/login";
    }

    @GetMapping( "/logout" )
    public String logout( HttpSession session ) {
        session.invalidate();

        return "redirect:/";
    }
}
