package ru.olegproj.training.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.olegproj.training.hibernate.model.BookEntity;
import ru.olegproj.training.hibernate.model.BookEntity_;
import ru.olegproj.training.hibernate.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

public class BookDao {

    protected SessionFactory sessionFactory;

    public BookDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<BookEntity> getBookEntitysList() {
        //Открывает сессию для соединеиня с бд + persist obj + CRUD
        Session session = sessionFactory.openSession();

        //строитель запросов в Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();
        //создаём билдер запросов
        CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
        //создаем (from - sql) -> root
        Root<BookEntity> root = cq.from(BookEntity.class);
//        Selection[] selections = {root.get(BookEntity_.name)}; //чтобы это заработало должны быть та такие конструкторы с параметрами, которые мы выбираем

        //создаём запрос с помощью постройщика запросов cq
//        cq.select(cb.construct(BookEntity.class, selections));
        cq.select(root);

        //с помощью сесси создаём и выполняем запрос
        List<BookEntity> BookEntitys = session.createQuery(cq).getResultList();

        //закрытие сесси
        session.close();

        return BookEntitys;
    }

    private Session getOpenSession() {
        return sessionFactory.openSession();
    }

    public void addBookEntity(BookEntity b) {
        Session session = getOpenSession();

        b.setName("Django");
        b.setId(5L);
        session.save(b);

        session.close();
    }
}
