package ru.olegproj.training.hibernate;

import ru.olegproj.training.hibernate.entity.Author;
import ru.olegproj.training.hibernate.entity.Book;

public class Start {
    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession(); //тестовое открытие сессии

        for(Author author : new AuthorHelper().getAuthorList()) {
            System.out.println("Author = " + author.getName());
        }

        for(Book book : new BookHelper().getBooksList()) {
            System.out.println("Book = " + book.getName());
        }

        HibernateUtil.getSessionFactory().close();
    }
}
