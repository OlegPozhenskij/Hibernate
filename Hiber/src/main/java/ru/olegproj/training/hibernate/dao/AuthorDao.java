package ru.olegproj.training.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.olegproj.training.hibernate.model.AuthorEntity;
import ru.olegproj.training.hibernate.model.AuthorEntity_;
import ru.olegproj.training.hibernate.util.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Random;

public class AuthorDao {

    protected SessionFactory sessionFactory;

    public AuthorDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<AuthorEntity> getAuthorEntityList() {
        //подготовка запроса
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder(); //строитель зпросов
        CriteriaQuery<AuthorEntity> cq = cb.createQuery(AuthorEntity.class); // создание запроса
        Root<AuthorEntity> root = cq.from(AuthorEntity.class); // from <Author.class> - в sql


//        Selection[] selections = {root.get(AuthorEntity_.id), root.get(AuthorEntity_.name)}; //  select {параметры после select} from

//        ParameterExpression<String> nameParam = cb.parameter(String.class, "name"); //параметр типа стринг имя параметра name - не имя столбца!

        //объект-конструктор запросов для Criteria API
        //cr.select(root).where(cb.like(root.get("name"), "Sam")); //условие просто для рута
        cq.select(root);


        //выполнение запроса
        Query<AuthorEntity> query = session.createQuery(cq);
//        query.setParameter("name", "%1%"); //условие на месте where

        List<AuthorEntity> results = query.getResultList();// здесь выполняется запрос!

        //закрытие сессии
        session.close();
        return results;
    }

    private Session getOpenSession() {
        return sessionFactory.openSession();
    }

    public void add200AuthorEntitys() {
        Session session = getOpenSession();
        session.beginTransaction();

        int numberOfWords = 100;

        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            AuthorEntity a = new AuthorEntity();

            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }

            a.setName(new String(word));
            a.setSecondName(new String(word) + "addsa");

            session.save(a);
//            if((i % 10) == 0) session.flush();
        }

        session.getTransaction().commit();
        session.close();
    }


    /*         ОСНОВА

    Session session = getOpenSession();

    session.beginTransaction();

    ::::тут пишется код запроса::::

    session.getTransaction().commit();

    session.close();
     */


    public void delete() {
        Session session = getOpenSession();
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder(); //строитель зпросов
        CriteriaDelete<AuthorEntity>    cd = cb.createCriteriaDelete(AuthorEntity.class); // создание запроса

        Root<AuthorEntity> root = cd.from(AuthorEntity.class); // from <Author.class> - в sql


        ParameterExpression<String> nameParam = cb.parameter(String.class, "name"); //параметр типа стринг имя параметра name - не имя столбца!
        ParameterExpression<String> secondNameParam = cb.parameter(String.class, "secondName"); //параметр типа стринг имя параметра name - не имя столбца!

        //объект-конструктор запросов для Criteria API
        //cr.select(root).where(cb.like(root.get("name"), "Sam")); //условие просто для рута


        //нет select сразу WHERE
        cd.where(
                cb.or(
                        cb.and(
                                cb.like(root.get(AuthorEntity_.name), nameParam),
                                cb.like(root.get(AuthorEntity_.secondName), secondNameParam)
                        ),
                        cb.equal(root.get(AuthorEntity_.name), "something")
                )
        );

        //выполнение запроса
        Query<AuthorEntity> query = session.createQuery(cd);
        query.setParameter("name", "%%"); //условие на месте where
        query.setParameter("secondName", "%%"); //условие на месте where

        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public void update() {
        Session session = getOpenSession();
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder(); //строитель зпросов
        CriteriaUpdate<AuthorEntity> critUpd = cb.createCriteriaUpdate(AuthorEntity.class); // создание запроса

        Root<AuthorEntity> root = critUpd.from(AuthorEntity.class); // from <Author.class> - в sql


        ParameterExpression<String> nameParam = cb.parameter(String.class, "name"); //параметр типа стринг имя параметра name - не имя столбца!
        ParameterExpression<String> secondNameParam = cb.parameter(String.class, "secondName"); //параметр типа стринг имя параметра name - не имя столбца!

        Expression<Integer> lenght = cb.length(root.get(AuthorEntity_.secondName));

        //объект-конструктор запросов для Criteria API
        //cr.select(root).where(cb.like(root.get("name"), "Sam")); //условие просто для рута


        //нет select сразу WHERE
        critUpd.set(AuthorEntity_.name, "surename").where(cb.greaterThan(lenght, 9));


        //выполнение запроса
        Query<AuthorEntity> query = session.createQuery(critUpd);

        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}
