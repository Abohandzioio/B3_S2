package com.ilci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilci.model.Promo;
import com.ilci.model.User;

public interface PromoRepository extends JpaRepository<Promo, Integer> {

    List<User> findByUsers( Promo p );

}
