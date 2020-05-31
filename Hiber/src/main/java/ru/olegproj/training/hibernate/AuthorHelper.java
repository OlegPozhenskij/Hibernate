package ru.olegproj.training.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.olegproj.training.hibernate.entity.Author;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorHelper {
    private SessionFactory sessionFactory;

    public AuthorHelper() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList() {
        //подготовка запроса
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder(); //строитель зпросов
        CriteriaQuery<Author> cr = cb.createQuery(Author.class); // создание запроса
        Root<Author> root = cr.from(Author.class); // from - в sql

        //объект-конструктор запросов для Criteria API
        cr.select(root).where(cb.like(root.get("name"), "Sam")); //условие

        //выполнение запроса
        List<Author> results = session.createQuery(cr).getResultList();

        //закрытие сессии
        session.close();
        return results;
    }
}
