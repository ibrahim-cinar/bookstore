package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.*;
import com.cinarcorp.bookstore.bookstore.dto.converter.BookDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.BookNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.GenreIdNotFoundException;
import com.cinarcorp.bookstore.bookstore.model.*;
import com.cinarcorp.bookstore.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDtoConverter bookDtoConverter;

    public BookService(BookRepository bookRepository, BookDtoConverter bookDtoConverter) {
        this.bookRepository = bookRepository;
        this.bookDtoConverter = bookDtoConverter;
    }


    public List<BookDto> getAllBook() {
        return bookRepository.findAll().stream().map(bookDtoConverter::convert).collect(Collectors.toList());
    }
    protected Book findBookById(String id){
        return bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book could not find by id"+id));
    }
    public BookDto getBookById(String id){

        return bookDtoConverter.convert(findBookById(id));
    }
    /**//********************************************************************************************************************//**/

    protected Book findBookByGenreId(String id){
        return bookRepository.getBookFromGenreId(id).orElseThrow(()-> new GenreIdNotFoundException("Genre id could not find"+id));
    }
    public BookDto getBookByGenreId(String id){
        return bookDtoConverter.convert(findBookByGenreId(id));
    }
    /**//********************************************************************************************************************//**/



    public BookDto createNewBook(CreateBookRequest createBookRequest) {

        Book book = createBookFromRequest(createBookRequest);

        List<Author> authors = createAuthorFromRequest(createBookRequest, book);

        List<Genre> genres = createGenreFromRequest(createBookRequest, book);

        List<Publisher> publishers = createPublisherFromRequest(createBookRequest, book);

        Inventory inventory = createInventoryFromRequest(createBookRequest,book);

        return bookDtoConverter.convert(bookRepository.save(book));
    }
    private Book createBookFromRequest(CreateBookRequest request){
        return  Book.builder()
                .bookName(request.getBookName())
                .title(request.getTitle())
                .ISBN(request.getISBN())
                .publisherYear(LocalDateTime.now())
                .price(request.getPrice())
                .build();
    }
    private List<Author> createAuthorFromRequest(CreateBookRequest request,Book book){
        List<AuthorDto> authors = request.getAuthors();
        if (authors == null) {
            return Collections.emptyList();
        }
        return request.getAuthors().stream()
                .map(authorRequest ->{Author author = Author.builder()
                        .firstName(authorRequest.getFirstName())
                        .lastName(authorRequest.getLastName())
                        .build();
                    book.addAuthor(author);
                    return author;
                }).collect(Collectors.toList());
    }
    private List<Genre> createGenreFromRequest(CreateBookRequest request, Book book){
        List<GenreDto> genres = request.getGenres();
        if (genres == null) {
            return Collections.emptyList();
        }
            return request.getGenres().stream()
                .map(genreRequest ->{Genre genre = Genre.builder().description(genreRequest.getDescription())
                        .build();
                    book.addGenres(genre);
                    return genre;
                }).collect(Collectors.toList());
    }
    private List<Publisher> createPublisherFromRequest(CreateBookRequest request, Book book){
        List<PublisherDto> publishers = request.getPublishers();
        if (publishers == null) {
            return Collections.emptyList();
        }

        return request.getPublishers().stream()
                .map(publisherRequest ->{Publisher publisher = Publisher.builder()
                        .country(publisherRequest.getCountry())
                        .firmName(publisherRequest.getFirmName())
                        .build();
                    book.addPublisher(publisher);
                    return publisher;
                }).collect(Collectors.toList());
    }
    private Inventory createInventoryFromRequest(CreateBookRequest request, Book book){
            return  new Inventory(
                    request.getInventory().getStockLevelUsed(),
                    request.getInventory().getStockLevelNew(),
                    book
            );
    }
}
