package com.aptech.springmvc.dao;

import com.aptech.springmvc.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public AuthorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Author> getAuthors() {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Author> theQuery = currentSession.createQuery("from Author ", Author.class);

        List<Author> authors = theQuery.getResultList();

        return authors;
    }
}