package com.ameszon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ameszon.model.ArticleCommandeKey;
import com.ameszon.model.LigneDeCommande;

public interface LDCRepository extends JpaRepository<LigneDeCommande, ArticleCommandeKey> {

}
