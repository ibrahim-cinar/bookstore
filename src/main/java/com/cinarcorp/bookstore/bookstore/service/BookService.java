package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.*;
import com.cinarcorp.bookstore.bookstore.dto.converter.BookDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.BookNotFoundException;
import com.cinarcorp.bookstore.bookstore.model.*;
import com.cinarcorp.bookstore.bookstore.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final BookDtoConverter bookDtoConverter;

    public BookService(BookRepository bookRepository, BookDtoConverter bookDtoConverter) {
        this.bookRepository = bookRepository;
        this.bookDtoConverter = bookDtoConverter;
    }


    public List<BookDto> getAllBook() {
        return bookRepository.findAll()
                .stream()
                .map(bookDtoConverter::convert)
                .collect(Collectors.toList());
    }
    protected Book findBookById(String id){
        return bookRepository.findById(id).
                orElseThrow(()-> new BookNotFoundException("Book could not find by id"+id));
    }
    public BookDto getBookById(String id){

        return bookDtoConverter.convert(findBookById(id));
    }
    protected Optional<Book> findBookByISBN(String isbn) {
        return bookRepository.findByISBN(isbn);
    }

    public BookDto getBookByISBN(String isbn){
        var bookISBN = findBookByISBN(isbn).orElseThrow(()->new BookNotFoundException("isbn number not found"+isbn));
        return bookDtoConverter.convert(bookISBN);
    }


    public BookDto createNewBook(CreateBookRequest createBookRequest) {
        if (createBookRequest == null || createBookRequest.getBookName() == null || createBookRequest.getISBN() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid book request");
        }

        String isbn = createBookRequest.getISBN();

        if (isbn.startsWith("0")|| isbn.length() != 13) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ISBN: ISBN cannot start with '0' and must be 13 digits");
        }

        //Check whether the book has been previously saved or not.
        if (bookRepository.existsByISBN(isbn)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A book with the same ISBN already exists");
        }
        Book book = createBookFromRequest(createBookRequest);
        Book savedBook = bookRepository.save(book);
        return bookDtoConverter.convert(savedBook);
    }


    private Book createBookFromRequest(CreateBookRequest request) {
        Book book = Book.builder()
                .bookName(request.getBookName())
                .title(request.getTitle())
                .ISBN(request.getISBN())
                .publisherYear(LocalDateTime.now())
                .price(request.getPrice())
                .build();

        createAuthorsFromRequest(request.getAuthors(), book);
        createGenresFromRequest(request.getGenres(), book);
        createPublishersFromRequest(request.getPublishers(), book);
        createInventoryFromRequest(request.getInventory(), book);
        return book;
    }


    private void createAuthorsFromRequest(List<AuthorDto> authors, Book book) {
        if (authors != null) {
            authors.forEach(authorDto -> {
                Author author = Author.builder()
                        .firstName(authorDto.getFirstName())
                        .lastName(authorDto.getLastName())
                        .build();
                book.addAuthor(author);
            });
        }
    }

    private void createGenresFromRequest(List<GenreDto> genres, Book book) {
        if (genres != null) {
            genres.forEach(genreDto -> {
                Genre genre = Genre.builder()
                        .description(genreDto.getDescription())
                        .build();
                book.addGenres(genre);
            });
        }
    }

    private void createPublishersFromRequest(List<PublisherDto> publishers, Book book) {
        if (publishers != null) {
            publishers.forEach(publisherDto -> {
                Publisher publisher = Publisher.builder()
                        .country(publisherDto.getCountry())
                        .firmName(publisherDto.getFirmName())
                        .build();
                book.addPublisher(publisher);
            });
        }
    }

    private void createInventoryFromRequest(InventoryDto inventoryDto, Book book) {
        if (inventoryDto != null) {
            Inventory inventory = new Inventory(
                    inventoryDto.getStockLevelUsed(),
                    inventoryDto.getStockLevelNew(),
                    book
            );
            book.setInventory(inventory);
        }
    }
    public BookDto updateBook(String isbn, UpdateBookRequest updateBookRequest) {
        Optional<Book> optionalBook = findBookByISBN(isbn);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setBookName(updateBookRequest.getBookName());
            book.setTitle(updateBookRequest.getTitle());
            book.setPublisherYear(updateBookRequest.getPublisherYear());
            book.setPrice(updateBookRequest.getPrice());

            Book updatedBook = bookRepository.save(book);
            return bookDtoConverter.convert(updatedBook);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not found");
        }
    }
    public void deleteAllAboutBook(String isbn){
        Optional<Book> optionalBook = findBookByISBN(isbn);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookRepository.delete(book);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not found");
    }

}
