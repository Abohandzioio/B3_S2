package com.intro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intro.model.Article;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
