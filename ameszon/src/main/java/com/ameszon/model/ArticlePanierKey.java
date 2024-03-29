package com.ameszon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ArticlePanierKey {

    @Column( name = "id_article" )
    private Integer articleId;

    @Column( name = "id_panier" )
    private Integer panierId;

    public ArticlePanierKey() {
    }

    public ArticlePanierKey( Integer articleId, Integer panierId ) {
        this.articleId = articleId;
        this.panierId = panierId;
    }

    @Override
    public String toString() {
        return "ArticlePanierKey [articleId=" + articleId + ", panierId=" + panierId + "]";
    }

}
