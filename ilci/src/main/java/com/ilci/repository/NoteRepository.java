package com.ilci.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilci.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}

