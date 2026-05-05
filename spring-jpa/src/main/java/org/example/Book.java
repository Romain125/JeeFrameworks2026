package org.example;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Title title;

    @ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private Set<Author> authors;

    private Type  type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="book_id", insertable = false, updatable = false)
    private Edition edition;

    @Transient
    private LocalDateTime dataFreshness;

    public Book(Title title, Set<Author> authors, Type type, Edition edition) {
        this.title = title;
        this.authors = authors;
        this.type = type;
        this.edition = edition;
        this.dataFreshness = LocalDateTime.now();
    }

    public Book() {
        this.dataFreshness = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + authors + '\'' +
                ", type=" + type +
                ", dataFreshness=" + dataFreshness +
                '}';
    }
}
