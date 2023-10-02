package com.cinarcorp.bookstore.bookstore.controller;

import com.cinarcorp.bookstore.bookstore.dto.BookDto;
import com.cinarcorp.bookstore.bookstore.dto.CreateBookRequest;
import com.cinarcorp.bookstore.bookstore.model.Book;
import com.cinarcorp.bookstore.bookstore.service.BookService;
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
    @GetMapping("/booksGenre/{id}")
    public ResponseEntity<BookDto>getBookByGenreId(@PathVariable String id){
        return  ResponseEntity.ok(bookService.getBookByGenreId(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> createNewBook(@RequestBody CreateBookRequest request){
        return ResponseEntity.ok(bookService.createNewBook(request));
    }
}
