package ru.olegproj.training.hibernate;

import org.jboss.logging.Logger;
import ru.olegproj.training.hibernate.dao.AuthorDao;

import ru.olegproj.training.hibernate.dao.BookDao;
import ru.olegproj.training.hibernate.model.AuthorEntity;

import ru.olegproj.training.hibernate.model.BookEntity;
import ru.olegproj.training.hibernate.util.HibernateUtil;

public class Start {
    private static final Logger LOG_AUT = Logger.getLogger( AuthorEntity.class.getName() );
    private static final Logger LOG_BOOK = Logger.getLogger( BookEntity.class.getName() );


    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession(); //тестовое открытие сессии

        AuthorDao authorDao = new AuthorDao();
//        for(AuthorEntity author : authorDao.getAuthorEntityList()) {
//            LOG_AUT.debug(author.getName());
//        }

        authorDao.getAuthorEntityList();

//        BookDao b = new BookDao();
//        b.getBookEntitysList();

        HibernateUtil.getSessionFactory().close();
    }
}
