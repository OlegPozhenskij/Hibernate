package ru.olegproj.training.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.olegproj.training.hibernate.entity.Author;
import ru.olegproj.training.hibernate.entity.Book;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookHelper {
    private SessionFactory sessionFactory;

    public BookHelper() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBooksList() {
        //Открывает сессию для соединеиня с бд + persist obj + CRUD
        Session session = sessionFactory.openSession();

        //строитель запросов в Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();
        //создаём билдер запросов
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        //создаем (from - sql) -> root
        Root<Book> root = cq.from(Book.class);

        //создаём запрос с помощью постройщика запросов cq
        cq.select(root);

        //с помощью сесси создаём и выполняем запрос
        List<Book> books = session.createQuery(cq).getResultList();

        //закрытие сесси
        session.close();

        return books;
    }
}
