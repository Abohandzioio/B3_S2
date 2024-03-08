package com.ilci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilci.model.Matiere;
import com.ilci.model.Promo;

public interface MatiereRepository extends JpaRepository<Matiere, Integer> {

    List<Matiere> findByPromo( Promo p );

}
