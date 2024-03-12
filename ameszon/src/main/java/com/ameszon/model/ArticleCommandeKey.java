package com.ameszon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ArticleCommandeKey {

    @Column( name = "id_article" )
    private Integer articleId;

    @Column( name = "id_commande" )
    private String  commandeId;

    public ArticleCommandeKey() {
    }

    public ArticleCommandeKey( Integer articleId, String commandeId ) {
        this.articleId = articleId;
        this.commandeId = commandeId;
    }

}
