package com.demo.bookstore.service;

import com.demo.bookstore.service.dto.BookDto;

import java.math.BigDecimal;
import java.util.List;

public interface BookStoreService {
    void addBook(BookDto bookDto);

    void removeBook(Long id);

    void updateBook(Long id, int quantityToUpdate);

    int getNoOfBooksById(Long id);

    List<BookDto> getAllBooks();

    List<BookDto> getBookByAuthorFilterPrice(String author, BigDecimal lowerPrice, BigDecimal higherPrice);
}
