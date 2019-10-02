package com.company.bookservice.Dto;


 book_id int not null auto_increment primary key,
 title varchar(50) not null,
 author varchar(50) not null

public class BookDao {
 @min(0)
 private int book_id;
 @Size(min=5, max=50)
 private String title;
 @Size(min=5, max=50)
 private String author;

}
