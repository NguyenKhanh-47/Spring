package com.aptech.springmvc.dao;

import com.aptech.springmvc.entity.Author;

import java.util.List;

public interface AuthorDAO {
    public List<Author> getAuthors();
}