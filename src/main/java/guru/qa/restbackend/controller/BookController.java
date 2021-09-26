package guru.qa.restbackend.controller;

import guru.qa.restbackend.domain.Book;
import guru.qa.restbackend.exception.BookNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


import static java.util.stream.Collectors.toList;

@RestController
public class BookController {

    Map<String, Book> books = Map.of(
            "0932633390", Book.builder()
                    .isbn("0932633390")
                    .title("The Deadline : A Novel about Project Management")
                    .authorId(1)
                    .build(),
            "0201006502", Book.builder()
                    .isbn("0201006502")
                    .title("The Mythical Man-Month : Essays on Software Engineering")
                    .authorId(2)
                    .build()
    );

    @GetMapping("books/")
    @ApiOperation("Get all books")
    public List<Book> getAllBooks() {
        return books.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(toList());
    }

    @PostMapping(value = "books/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Add a new book")
    public Book addBook(@RequestBody Book book) {
        return Book.builder()
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .authorId(book.getAuthorId())
                .build();
    }

    @GetMapping("books/isbn/{isbn}")
    @ApiOperation("Get book by ISBN")
    public List<Book> getBookByIsbn(@PathVariable("isbn") String isbn) {

        if (books.containsKey(isbn)) {
        return books.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(book -> book.getIsbn().equals(isbn))
                .collect(toList());}
        else throw new BookNotFoundException(HttpStatus.NOT_FOUND);
    }
}
