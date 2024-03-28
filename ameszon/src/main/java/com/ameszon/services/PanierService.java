package com.ameszon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ameszon.model.ArticlePanier;
import com.ameszon.model.User;
import com.ameszon.repository.PanierRepository;

@Service
public class PanierService {

    @Autowired
    PanierRepository panierRepository;

    public int totalPanier( User u ) {

        int nbArt = 0;

        if ( panierRepository.findByUser( u ) != null )
            for ( ArticlePanier ap : panierRepository.findByUser( u ).getPaniers() )
                nbArt += ap.getQuantity();

        return nbArt;
    }
}
