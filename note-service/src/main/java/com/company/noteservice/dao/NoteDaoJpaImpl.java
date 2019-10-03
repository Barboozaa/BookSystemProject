package com.company.noteservice.dao;

import com.company.noteservice.dto.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpaDao")
public interface NoteDaoJpaImpl extends JpaRepository<Note, Integer> {

    List<Note> findNotesByBookId(int id);

}
