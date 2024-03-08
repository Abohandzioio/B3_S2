package com.ilci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilci.model.Promo;
import com.ilci.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginAndMdp( String login, String mdp );

    User findUserByLogin( String login );

    List<User> findByPromo( Promo p );

}
