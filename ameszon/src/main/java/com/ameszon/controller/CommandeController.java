package com.ameszon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ameszon.model.Article;
import com.ameszon.model.ArticleCommandeKey;
import com.ameszon.model.ArticlePanier;
import com.ameszon.model.Commande;
import com.ameszon.model.LigneDeCommande;
import com.ameszon.model.Panier;
import com.ameszon.repository.ArtPanierRepository;
import com.ameszon.repository.ArticleRepository;
import com.ameszon.repository.CategorieRepository;
import com.ameszon.repository.CommandeRepository;
import com.ameszon.repository.LDCRepository;
import com.ameszon.repository.UserRepository;
import com.ameszon.services.PanierService;

@Controller
@RequestMapping( "/commande" )
public class CommandeController {

    @Autowired
    ArtPanierRepository artPanierRepository;
    @Autowired
    PanierService       panierService;
    @Autowired
    CommandeRepository  commandeRepository;
    @Autowired
    ArticleRepository   articleRepository;
    @Autowired
    LDCRepository       ldcRepository;
    @Autowired
    UserRepository      userRepository;
    @Autowired
    CategorieRepository categorieRepository;

    @GetMapping( "/dashboard" )
    public String dashboard( Model model ) {

        model.addAttribute( "users", userRepository.findUserByStatut(
                "CLIENT" ).size() );
        model.addAttribute( "articles", articleRepository.count() );
        model.addAttribute( "cmds", commandeRepository.count() );
        model.addAttribute( "ldc",
                ldcRepository.findDistinctByArticle() );
        model.addAttribute( "totalLDC", ldcRepository.totalLDC() );

        return "admin/index";
    }

    @PostMapping( "/valider" )
    public String showPanier( Panier panier, Model model ) {
        // Recup des articles dans le panier
        List<ArticlePanier> artPanier = artPanierRepository.findAllByPanierId( panier.getId() );

        // Création de la commande
        Commande cmd = new Commande( panier.getUser() );
        commandeRepository.save( cmd );

        // PARCOURS LDC
        for ( ArticlePanier artP : artPanier ) {
            Article article = artP.getArticle();

            // CLE PRIMAIRE POUR LA RELATION LDC ENTRE ARTICLE ET CMD
            ArticleCommandeKey artCmdkey = new ArticleCommandeKey( article.getId(), cmd.getId() );

            LigneDeCommande ldc = new LigneDeCommande( artCmdkey, article, cmd, artP.getQuantity(), artP.getPrix() );

            // MAJ QTTY ARTICLE
            article.setStock( article.getStock() - artP.getQuantity() );
            articleRepository.save( article );

            // SAVE LDC
            ldcRepository.save( ldc );

            // DELETE LINE IN PANIERARTICLE
            artPanierRepository.delete( artP );
        }

        return "redirect:/";
    }
}
