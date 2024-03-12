package com.intro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    private double prix;
public Article() {
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getLibelle() {
	return libelle;
}

public void setLibelle(String libelle) {
	this.libelle = libelle;
}

public double getPrix() {
	return prix;
}

public void setPrix(double prix) {
	this.prix = prix;
}

@Override
public String toString() {
	return "Article [id=" + id + ", libelle=" + libelle + ", prix=" + prix + "]";
}





}
