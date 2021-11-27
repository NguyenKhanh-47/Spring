package com.aptech.springmvc.service;

import com.aptech.springmvc.dao.AuthorDAO;
import com.aptech.springmvc.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorDAO authorDAO;

    @Autowired
    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }
    @Override
    @Transactional
    public List<Author> getAuthors() {
        return authorDAO.getAuthors();
    }

}
