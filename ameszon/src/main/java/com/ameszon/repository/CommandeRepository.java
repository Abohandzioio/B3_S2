package com.ameszon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ameszon.model.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, String> {

}
