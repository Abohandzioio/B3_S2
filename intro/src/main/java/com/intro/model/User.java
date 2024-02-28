package com.intro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String  prenom;
    private String  nom;
    private int     age;

    public User() {
    }

    public User( int id, String prenom, String nom, int age ) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", age=" + age + "]";
    }

}
