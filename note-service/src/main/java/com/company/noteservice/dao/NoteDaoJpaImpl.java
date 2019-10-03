package com.company.noteservice.dao;

import com.company.noteservice.dto.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaDao")
public interface NoteDaoJpaImpl extends JpaRepository<Note, Integer> {

}
