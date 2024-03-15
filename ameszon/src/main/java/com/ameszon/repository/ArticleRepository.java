package com.ameszon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ameszon.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
