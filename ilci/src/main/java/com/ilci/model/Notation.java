package com.ilci.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notation {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Min( 0 )
    @Max( 18 )
    @NotNull
    private int     note;

    @ManyToOne
    private User    etudiant;

    @ManyToOne
    private Matiere matiere;

    @Override
    public String toString() {
        return "Notation [id=" + id + ", note=" + note + "]";
    }

}
