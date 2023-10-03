package com.cinarcorp.bookstore.bookstore.controller;

import com.cinarcorp.bookstore.bookstore.dto.BookDto;
import com.cinarcorp.bookstore.bookstore.model.Genre;
import com.cinarcorp.bookstore.bookstore.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/{genreId}/books")
    public ResponseEntity<List<BookDto>> getBooksByGenreId(@PathVariable String genreId) {
        return ResponseEntity.ok(genreService.getBooksByGenreId(genreId));
    }
}