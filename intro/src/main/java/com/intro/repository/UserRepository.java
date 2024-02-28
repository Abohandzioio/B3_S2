package com.intro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intro.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
