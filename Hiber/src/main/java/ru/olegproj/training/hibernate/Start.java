package ru.olegproj.training.hibernate;

import org.hibernate.Session;
import ru.olegproj.training.hibernate.entity.Author;

public class Start {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        for(Author author : new AuthorHelper().getAuthorList()) {
            System.out.println("Author = " + author.getName());
        }

        HibernateUtil.getSessionFactory().close();
    }
}
