package ru.olegproj.training.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        final StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder() // содержит сервисы нужные Hibernate
                .configure("ru/olegproj/training/hibernate.cfg.xml")                          // ипи выполнении и загрузке
                .build();
        try {
            SessionFactory sessionFactory = new MetadataSources(standardRegistry) // настраивает связь
                    .buildMetadata()                                              // между классами и бд
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(standardRegistry);
            throw e;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
