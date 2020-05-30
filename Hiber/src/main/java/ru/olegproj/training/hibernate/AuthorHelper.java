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
        //открываем сессию для манипуляции с persistent objects
        Session session = sessionFactory.openSession();
        session.get(Author.class, 1L); //получение объекта по id (Autor - таблица)


        //подготовка запроса
        //объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Author.class); // с помощью cq мы формеруем запрос
        Root<Author> root = cq.from(Author.class);// Какая основная таблица будет - Author
        cq.select(root); //взять все записи рута


        //выполнение запроса
        Query query = session.createQuery(cq);
        List<Author> authors = query.getResultList();


        //закрытие сессии
        session.close();
        return authors;
    }
}
