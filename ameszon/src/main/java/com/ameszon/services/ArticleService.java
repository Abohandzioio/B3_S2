package com.ameszon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ameszon.model.Article;
import com.ameszon.repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    public Article getArticleById( Integer id ) {
        Optional<Article> art = articleRepository.findById( id );

        return art.isPresent() ? art.get() : null;
        // if( art.isPresent() )
        // return art.get();
        // return null;
    }

    public Article createArticle( Article article ) {
        return articleRepository.save( article );
    }

    public Article updateArticle( Integer id, Article articleToUp ) {
        Optional<Article> optArt = articleRepository.findById( id );

        if ( optArt.isPresent() ) {
            Article artInBd = optArt.get();

            artInBd.setCategorie( articleToUp.getCategorie() );
            artInBd.setType( articleToUp.getType() );
            artInBd.setStock( articleToUp.getStock() );
            artInBd.setPrix( articleToUp.getPrix() );
            artInBd.setDescription( articleToUp.getDescription() );
            artInBd.setLibelle( articleToUp.getLibelle() );

            return articleRepository.save( artInBd );
        }

        return null;
    }

    public void deleteArticleById( Integer id ) {
        articleRepository.deleteById( id );
    }

    public void deleteArticle( Article article ) {
        articleRepository.delete( article );
    }

}
