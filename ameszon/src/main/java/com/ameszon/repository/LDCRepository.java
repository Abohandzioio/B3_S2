package com.ameszon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ameszon.model.ArticleCommandeKey;
import com.ameszon.model.LigneDeCommande;

public interface LDCRepository extends JpaRepository<LigneDeCommande, ArticleCommandeKey> {

    @Query( "SELECT sum(l.quantity) FROM LigneDeCommande l" )
    int totalLDC();

    @Query( "SELECT COUNT(DISTINCT l.article) FROM LigneDeCommande l" )
    int findDistinctByArticle();

}
