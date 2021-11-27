package com.aptech.springmvc.dao;

import com.aptech.springmvc.constant.SortBookColumn;
import com.aptech.springmvc.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Book> getBooks(int theSortField) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        String sortField = null;

        switch (theSortField) {
            case SortBookColumn.TITLE:
                sortField = "title";
                break;
            case SortBookColumn.PRICE:
                sortField = "price";
                break;
            case SortBookColumn.AUTHOR:
                sortField = "author";
                break;
            case SortBookColumn.RELEASE_DATE:
                sortField = "releaseDate";
                break;
            default:
                sortField = "category";
        }

        Query<Book> theQuery = currentSession.createQuery("from Book order by " + sortField, Book.class);

        List<Book> books = theQuery.getResultList();

        return books;
    }

    @Override
    public void saveBook(Book theBook) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // if the primaryKey is empty, then INSERT else UPDATE.
        currentSession.saveOrUpdate(theBook);
    }

    @Override
    public Book getBook(int theId) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Book theBook = currentSession.get(Book.class, theId);

        return theBook;
    }

    @Override
    public void deleteBook(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from Book where id=:bookId");
        theQuery.setParameter("bookId", theId);
        theQuery.executeUpdate();
    }

    @Override
    public List<Book> searchBooks(String theSearchName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery = null;

        if (theSearchName != null & theSearchName.trim().length() > 0) {
            theQuery = currentSession.createQuery("from Book where lower(title) like :theName " +
                    "or lower(author) like :theName " + "or lower(content) like :theName", Book.class);

            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        } else {
            theQuery = currentSession.createQuery("from Book", Book.class);
        }

        List<Book> books = theQuery.getResultList();

        return books;
    }
}