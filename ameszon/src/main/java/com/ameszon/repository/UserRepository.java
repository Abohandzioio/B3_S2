package com.ameszon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ameszon.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginAndMdp( String string, String string2 );

}
