package com.ilci.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


@Entity
public class Note {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	 @ManyToOne
	    private User user; 
	 @ManyToOne
	    private Matiere matiere;
	 
	   private Integer noteValue;
	   
	   
	    
	    
		
		
	    
}

