package com.books.dao;

import com.books.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
        System.out.println("Book saved. Book detail: " + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
        System.out.println("Book update. Book detail: " + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        if (book != null) {
            session.delete(book);
            System.out.println("Book removed. Book detail: " + book);
        } else {
            System.out.println("Book with this number does not exist ");
        }
    }

    @Override
    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        if (book != null) {
            System.out.println("Book load. Book detail: " + book);
            return book;
        }
        System.out.println("Book with this number does not exist ");
        return null;
    }

    @Override
    public List<Book> getBooks() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book").list();
        System.out.println("BookList: ");
        for (Book book : bookList) {
            System.out.println("Book load. Book detail: " + book);
        }
        return bookList;
    }
}
