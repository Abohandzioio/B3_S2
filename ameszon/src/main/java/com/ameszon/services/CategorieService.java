package com.ameszon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ameszon.model.Categorie;
import com.ameszon.repository.CategorieRepository;

@Service
public class CategorieService {

    @Autowired
    CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie createCat( Categorie categorie ) {
        return categorieRepository.save( categorie );
    }

    public void deleteCatById( Integer id ) {
        categorieRepository.deleteById( id );
    }

    public void deleteCat( Categorie categorie ) {
        categorieRepository.delete( categorie );
    }

    public Optional<Categorie> getCategorieById( Integer id ) {
        return categorieRepository.findById( id );
    }

    public Categorie updateCategorie( Integer id, Categorie catToUp ) {
        Optional<Categorie> OptCat = categorieRepository.findById( id );

        if ( OptCat.isPresent() ) {
            Categorie categorieInBd = OptCat.get();
            categorieInBd.setNom( catToUp.getNom() );

            return categorieRepository.save( categorieInBd );
        }

        return null;
    }

}
