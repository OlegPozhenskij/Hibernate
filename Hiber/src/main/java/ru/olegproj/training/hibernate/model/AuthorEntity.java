package ru.olegproj.training.hibernate.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "author", schema = "testhibernate")
public class AuthorEntity extends BaseEntity {

    @Column(name = "second_name", nullable = true, length = 100)
    private String secondName;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<BookEntity> books = new ArrayList<>();

    public AuthorEntity() {
    }

    public AuthorEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Basic
    @Column(name = "second_name")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(secondName, that.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName);
    }
}
