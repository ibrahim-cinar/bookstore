package com.cinarcorp.bookstore.bookstore.controller;

import com.cinarcorp.bookstore.bookstore.dto.BookDto;
import com.cinarcorp.bookstore.bookstore.model.Author;
import com.cinarcorp.bookstore.bookstore.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<BookDto>> getBooksByAuthorId(@PathVariable String authorId) {
        return ResponseEntity.ok(authorService.getBooksByAuthorId(authorId));
    }
    @GetMapping()
    public ResponseEntity<List<BookDto>> getBooksByAuthorName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        List<BookDto> booksByAuthor = authorService.getBookByAuthorName(firstName, lastName);

        if (booksByAuthor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(booksByAuthor);
        }

        return ResponseEntity.ok(booksByAuthor);
    }
}
