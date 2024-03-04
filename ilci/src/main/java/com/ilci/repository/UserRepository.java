package com.ilci.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilci.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginAndMdp( String login, String mdp );

    User findUserByLogin( String login );

}
