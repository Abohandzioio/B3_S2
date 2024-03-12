package com.ilci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilci.model.Notation;
import com.ilci.model.Promo;

public interface NotationRepository extends JpaRepository<Notation, Integer> {

List<Notation> findByMatiere_Promo(Promo promo);
    
    List<Notation> findByPromo(Promo promo);

}
