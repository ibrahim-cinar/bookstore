package com.cinarcorp.bookstore.bookstore.controller;

import com.cinarcorp.bookstore.bookstore.dto.BookDto;
import com.cinarcorp.bookstore.bookstore.dto.CreateBookRequest;
import com.cinarcorp.bookstore.bookstore.dto.UpdateBookRequest;
import com.cinarcorp.bookstore.bookstore.model.Book;
import com.cinarcorp.bookstore.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook(){
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable String bookId){
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }
    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> getBookByISBN(@PathVariable String isbn){
        return ResponseEntity.ok(bookService.getBookByISBN(isbn));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDto> createBook(@RequestBody CreateBookRequest createBookRequest) {
        return ResponseEntity.ok(bookService.createNewBook(createBookRequest));
    }
    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto>updateBook(@PathVariable String isbn,@RequestBody UpdateBookRequest updateBookRequest){
        return ResponseEntity.ok(bookService.updateBook(isbn,updateBookRequest));
    }
    @DeleteMapping("/{isbn}")
    public ResponseEntity<BookDto>deleteAllAboutBook(@PathVariable String isbn){
        bookService.deleteAllAboutBook(isbn);
        return ResponseEntity.ok().build();
    }
}
