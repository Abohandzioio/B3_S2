package com.ameszon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ameszon.model.ArticlePanier;
import com.ameszon.model.ArticlePanierKey;

public interface ArtPanierRepository extends JpaRepository<ArticlePanier, ArticlePanierKey> {

    List<ArticlePanier> findAllByPanierId( Integer id );

}
