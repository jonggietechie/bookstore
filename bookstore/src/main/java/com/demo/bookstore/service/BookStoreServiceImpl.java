package com.demo.bookstore.service;

import com.demo.bookstore.domain.Book;
import com.demo.bookstore.domain.BookRepo;
import com.demo.bookstore.service.dto.BookDto;
import com.demo.bookstore.service.exceptions.BookNotFoundException;
import com.demo.bookstore.service.exceptions.DuplicateResourceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);
    private final BookRepo bookRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public BookStoreServiceImpl(BookRepo bookRepo, ModelMapper modelMapper) {
        this.bookRepo = bookRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void addBook(BookDto bookDto) {
        //Check if bookDto is previously present
        Optional<Book> bookById = bookRepo.findById(bookDto.getId());

        if(bookById.isPresent()){
            throw new DuplicateResourceException("Book with same id present. " +
                    "Either use update methods to update the book counts or use addBook(Long id, int quantityToAdd) methods");
        } else {
            LOGGER.info("No Duplicates found.");
            //Map bookDto to book
            Book book = modelMapper.map(bookDto, Book.class);

            //Save to book
            bookRepo.save(book);
        }
    }

    @Override
    public void removeBook(Long id) {
        Optional<Book> bookById = bookRepo.findById(id);
        if (bookById.isPresent()) {
            // Book with the given ID exists, so delete it
            bookRepo.deleteById(id);
            LOGGER.info("Book with ID " + id + " removed successfully.");
        } else {
            LOGGER.info("Book with ID " + id + " not found. No action taken.");
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
    }

    @Override
    public void updateBook(Long id, int quantityToUpdate) {
        //Get the book by id
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id:" + id + " is not registered. Use addBook to register."));
        LOGGER.info("The book with id " + id + " is registered");

        book.setQuantity(quantityToUpdate);

        bookRepo.save(book);
    }

    @Override
    public int getNoOfBooksById(Long id) {
        Optional<Book> book = bookRepo.findById(id);

        //If book is present get Total Quantity or else return 0
        return book.isPresent() ? book.get().getQuantity() : 0;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        return mapBookListToBooDtoList(books);
    }

    @Override
    public List<BookDto> getBookByAuthorFilterPrice(String author, BigDecimal lowPrice, BigDecimal higherPrice) {

        //if the status is Available, gives list of books which are available
        LOGGER.info("Fetch all the books by Author and Filter by price range.");
        List<Book> book = bookRepo.findAllBookByCAuthorFilterPrice(author, lowPrice, higherPrice);
        return mapBookListToBooDtoList(book);
    }


    //Convert List of books to List of bookDto
    private List<BookDto> mapBookListToBooDtoList(List<Book> books) {
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }


}
