package com.ameszon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ameszon.model.Panier;
import com.ameszon.model.User;

public interface PanierRepository extends JpaRepository<Panier, Integer> {

    Panier findByUser( User u );

}
