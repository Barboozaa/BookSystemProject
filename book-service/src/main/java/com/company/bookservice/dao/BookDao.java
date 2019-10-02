package com.company.bookservice.dao;

import com.company.bookservice.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaDao")
public interface BookDao extends JpaRepository<Book, Integer> {}
