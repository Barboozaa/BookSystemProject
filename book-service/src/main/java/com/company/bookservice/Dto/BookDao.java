package com.company.bookservice.Dto;

public class BookDao {
 @min(0)
 private int book_id;
 @Size(min=5, max=50)
 private String title;
 @Size(min=5, max=50)
 private String author;

 public Bookdao(){}

 public BookDao(int book_id, String title, String author) {
  this.book_id = book_id;
  this.title = title;
  this.author = author;
 }

 public int getBook_id() {
  return book_id;
 }

 public void setBook_id(int book_id) {
  this.book_id = book_id;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getAuthor() {
  return author;
 }

 public void setAuthor(String author) {
  this.author = author;
 }
}
