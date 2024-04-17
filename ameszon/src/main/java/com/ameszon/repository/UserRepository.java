package com.ameszon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ameszon.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginAndMdp( String string, String string2 );

    // @Query( "SELECT count(u.statut) FROM User u WHERE u.statut = 'CLIENT'" )
    public List<User> findUserByStatut( String client );

}
