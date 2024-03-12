package com.intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.intro.model.Article;
import com.intro.repository.ArticleRepository;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping
    public String afficher(Model model) {
    	Article article = new Article();
    	article.setLibelle("PC");
    	model.addAttribute("article", article);
    	return "article/index1";
    	
    }
    
    @PostMapping("/creer")
    public String creerArticle(@ModelAttribute Article article) {
        articleRepository.save(article);
    	
		return "redirect:/articles/lire";
    	
    }
    
    @GetMapping("/lire")
    public String lireArticles(Model model) {
    	List<Article> listArticle= articleRepository.findAll();
    	model.addAttribute("articles",listArticle);
        return "article/show";
        
    }

    @GetMapping("/supprimer/{id}")
   public String deleteArticle(@PathVariable int id) {
        articleRepository.deleteById(id);
        return "redirect:/articles/lire";
    }
}
