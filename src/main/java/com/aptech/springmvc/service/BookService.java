package com.aptech.springmvc.service;

import com.aptech.springmvc.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> getBooks(int theSortField);
    public void saveBook(Book theBook);
    public Book getBook(int theId);
    public void deleteBook(int theId);
    public List<Book> searchBooks(String theSearchName);
}
