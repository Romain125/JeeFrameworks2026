package org.example;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private BookRepository bookRepository;
    private TraceService traceService;

    public BookService(BookRepository bookRepository, TraceService traceService) {
        this.bookRepository = bookRepository;
        this.traceService = traceService;
    }

    @Transactional
    public void exercices() {
        creation_de_livres();
        magic_method_par_type();
        magic_method_intermediaire();
        magic_method_embedded();
        //creation_edition();
    }

    public void creation_de_livres() {
        System.out.println("--------------Création de livres----------------");
        Book book1 = createBook("The Lord ot the Rings", "Le seigneur des anneaux",
                "JRR", "Tolkien",
                Type.ADVENTURE,
                "Folio Junior");
        bookRepository.save(book1);

        Book book2 = createBook("Harry Potter", "Harry Potter",
                "JK", "Rowling",
                Type.ADVENTURE,
                "Hachette");
        bookRepository.save(book2);

        Book book3 = createBook("Pride and Prejudice", "Orgueil et Préjugés",
                "Jane", "Austen",
                Type.LOVE,
                "Folio Classique");
        bookRepository.save(book3);

        List<Book> allBooks = bookRepository.findAll();
        allBooks.forEach(System.out::println);
    }

    private void magic_method_par_type() {
        System.out.println("--------------Magic method : par type----------------");
        List<Book> allLoveBooks = bookRepository.findByType(Type.LOVE);
        allLoveBooks.forEach(System.out::println);
    }

    private void magic_method_intermediaire() {
        System.out.println("--------------Magic method : top 3 ordered by ID----------------");
        List<Book> top3ByOrderByIdDesc = bookRepository.findTop3ByOrderByIdDesc();
        top3ByOrderByIdDesc.forEach(System.out::println);
    }

    private void magic_method_embedded() {
        System.out.println("--------------Magic method sur embedded/embeddable----------------");
        Book pride = bookRepository.findFirstByTitle_OriginalTitleContaining("Pride");
        System.out.println(pride);
    }

    private Book createBook(String originalTitle, String frTitle, String authorFirstName, String authorLastName, Type type, String edition) {
        Title title = new Title(originalTitle, frTitle);
        Author author = new Author(authorFirstName, authorLastName);
        Edition edition1 = new Edition(edition);
        return new Book(title, Set.of(author), type, edition1);
    }


    @Transactional
    public void exercices2() {
        System.out.println("---------------Exercice de @Transactional---------------");
        //On sauvegarde un livre
        Book book = createBook("Also sprach Zarathustra", "Ainsi parlait Zarathoustra",
                "Friedrich", "Nietzsche",
                Type.PHILOSOPHY,
                "Livre de poche - Classique");
        bookRepository.save(book);

        //Ca plante !
        traceService.trace(book);

        //Et pouf ! Rollback ! La magie du @Transactional !
        //Vérifiez en base que la sauvegarde a bien été 'annulée'
    }
}

