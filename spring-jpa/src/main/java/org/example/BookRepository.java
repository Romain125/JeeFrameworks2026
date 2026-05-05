package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByType(Type type);
    List<Book> findTop3ByOrderByIdDesc();
    Book findFirstByTitle_OriginalTitleContaining(String partOfTitle);
}
