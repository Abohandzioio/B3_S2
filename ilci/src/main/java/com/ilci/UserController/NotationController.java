// package com.ilci.UserController;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
//
// import com.ilci.model.Notation;
// import com.ilci.model.Promo;
// import com.ilci.repository.MatiereRepository;
// import com.ilci.repository.NotationRepository;
// import com.ilci.repository.PromoRepository;
// import com.ilci.repository.UserRepository;
//
// import jakarta.validation.Valid;
//
// @Controller
// public class NotationController {
//
// @Autowired
// NotationRepository notationRepository;
// @Autowired
// PromoRepository promoRepository;
// @Autowired
// UserRepository userRepository;
// @Autowired
// MatiereRepository matiereRepository;
//
// @GetMapping( "/notations" )
// public String index( Model model ) {
// model.addAttribute( "notes", notationRepository.findAll() );
// return "note/index";
// }
//
// @GetMapping( "/notations/add/{promo_id}" )
// public String new_note( Model model, @PathVariable int promo_id ) {
// Promo p = promoRepository.findById( promo_id ).get();
//
// model.addAttribute( "etudiants", userRepository.findByPromo( p ) );
// model.addAttribute( "matieres", matiereRepository.findByPromo( p ) );
// model.addAttribute( "notation", new Notation() );
// return "note/new";
// }
//
// @PostMapping( "/notations/create" )
// public String create( @Valid Notation notation, BindingResult result, Model
// model ) {
//
// if ( result.hasErrors() ) {
// model.addAttribute( "etudiants", userRepository.findByPromo(
// notation.getEtudiant().getPromo() ) );
// model.addAttribute( "matieres", matiereRepository.findByPromo(
// notation.getMatiere().getPromo() ) );
// return "note/new";
// }
//
// notationRepository.save( notation );
//
// return "redirect:/notations";
// }
//
// // Afficher les notes par promo
// @GetMapping("/notations/{promo_id}")
// public String notesByPromo(Model model, @PathVariable int promo_id) {
// Promo promo = promoRepository.findById(promo_id).orElse(null);
// if (promo != null) {
// model.addAttribute("notes", notationRepository.findByPromo(promo));
// return "note/by_promo";
// } else {
// return "redirect:/notations";
// }
// }
// }
