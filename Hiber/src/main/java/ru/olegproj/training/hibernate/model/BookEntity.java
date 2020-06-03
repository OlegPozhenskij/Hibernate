package ru.olegproj.training.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "testhibernate")
public class BookEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "author_id") //сязывание пололя с конкретным обьектом типа автор
    private AuthorEntity author;

    public BookEntity() {
    }

    public BookEntity(String name) {
        super(name);
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "author=" + author +
                '}';
    }

}
