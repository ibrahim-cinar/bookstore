package com.cinarcorp.bookstore.bookstore.controller;

import com.cinarcorp.bookstore.bookstore.dto.BookDto;
import com.cinarcorp.bookstore.bookstore.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }
    @GetMapping("/firmName/{firmName}")
    public ResponseEntity<List<BookDto>> getBookByFirmName(@PathVariable String firmName) {
        List<BookDto> publisherBookByFirmName = publisherService.getBookByFirmName(firmName);

        if (publisherBookByFirmName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(publisherBookByFirmName);
        }

        return ResponseEntity.ok(publisherBookByFirmName);
    }

}
