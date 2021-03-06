package com.aptech.springmvc.dao;

import com.aptech.springmvc.constant.SortCustomerColumn;
import com.aptech.springmvc.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers(int theSortField) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        String sortField = null;

        switch (theSortField) {
            case SortCustomerColumn.FIRST_NAME:
                sortField = "firstName";
                break;
            case SortCustomerColumn.EMAIL:
                sortField = "email";
                break;
            default:
                sortField = "lastName";
        }

        Query<Customer> theQuery = currentSession.createQuery("from Customer order by " + sortField, Customer.class);

        List<Customer> customers = theQuery.getResultList();
        System.out.println("dfdfdfdfdf" + customers);

        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // if the primaryKey is empty, then INSERT else UPDATE.
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Customer theCustomer = currentSession.get(Customer.class, theId);

        return theCustomer;
    }

    @Override
    public void deleteCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);
        theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery = null;

        if (theSearchName != null & theSearchName.trim().length() > 0) {
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName " +
                    "or lower(lastName) like :theName", Customer.class);

            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        } else {
            theQuery = currentSession.createQuery("from Customer", Customer.class);
        }

        List<Customer> customers = theQuery.getResultList();

        return customers;
    }
}