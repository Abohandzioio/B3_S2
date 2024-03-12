package com.ilci.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ilci.model.Note;
import com.ilci.repository.MatiereRepository;
import com.ilci.repository.NoteRepository;
import com.ilci.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class NoteController {
	 @Autowired
	    NoteRepository noteRepository;
	 
	    @Autowired
	    UserRepository userRepository;
	    
	    @Autowired
	    MatiereRepository matiereRepository;

	    @GetMapping( "/notes" )
	    public String index( Model model ) {
	        model.addAttribute( "note", new Note() );
	        model.addAttribute( "notes", noteRepository.findAll() );
	        
	        return "notes/index";
	    }
	    
	    @GetMapping( "/note/add" )
	    public String ajouter( Model model ) {
	        Note note = new Note();
	        model.addAttribute( "note", note );
	        model.addAttribute("users",userRepository.findAll() );
	        model.addAttribute("matieres", matiereRepository.findAll() );

	        return "notes/formulaire";
	    }
       
	    @PostMapping( "/note/insert" )
	    public String insert( @Valid Note note, BindingResult result, Model model ) {

	        if ( result.hasErrors() ) {
	        	 model.addAttribute( "note", note );
	 	        model.addAttribute("users",userRepository.findAll() );
	 	        model.addAttribute("matieres", matiereRepository.findAll() );

	            return "notes/formulaire";
	        }

	        

	        noteRepository.save( note );
	        return "redirect:/notes";
	    }

	    @GetMapping( "/notes/update/{id}" )
	    public String update( @PathVariable int id, Model model ) {
	        Note note = noteRepository.findById( id ).get();

	        model.addAttribute( "note", note );
	        model.addAttribute("users",userRepository.findAll() );
 	        model.addAttribute("matieres", matiereRepository.findAll() );


	        return "notes/formulaire";
	    }
	    
	    @GetMapping( "/notes/delete/{id}" )
	    public String delete( @PathVariable int id ) {
	        Note note = noteRepository.findById( id ).get();
	        noteRepository.delete( note );
	        return "redirect:/notes";
	    }
}
