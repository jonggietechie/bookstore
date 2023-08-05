package com.demo.bookstore.controller;


import com.demo.bookstore.service.BookStoreService;
import com.demo.bookstore.service.dto.BookDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * Controller for the bookstore inventory
 * 1) Add a book to the inventory
 * 2) Remove a book from the inventory
 * 3) Update the quantity in stock for a given book
 * 4) Retrieve the quantity in stock for a given book.
 * 5) List all books in the inventory.
 * 6) Search Books based author and filter by price range
 */
@RestController
@RequestMapping("/api")
@Api(value = "Bookstore Inventory Controller", description = "Bookstore REST Endpoints.")
public class BookStoreController {
    private final BookStoreService bookStoreService;
    @Autowired
    public BookStoreController(BookStoreService bookStoreservice) {this.bookStoreService = bookStoreservice;}

    /**
     * 1) Add a book to the inventory
     *
     * @param bookDto
     */

    @ApiOperation(value="Add New Book")
    @PostMapping("/add-new-book")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@Valid @RequestBody BookDto bookDto) {bookStoreService.addBook(bookDto);}


    /**
     * 2) Remove a book from the inventory
     *
     * @param id
     */
    @ApiOperation(value="Remove a book")
    @DeleteMapping("/remove-book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeBook(@PathVariable Long id) {bookStoreService.removeBook(id);}

    /**
     * 3) Update the quantity in stock for a given book
     *
     * @param id
     * @param quantityToUpdate
     */
    @ApiOperation(value = "Update books quantity")
    @PutMapping("/update-book/{id}/{quantityToUpdate}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id,
                        @PathVariable int quantityToUpdate) {
        bookStoreService.updateBook(id, quantityToUpdate);
    }
    /**
     * 4) Retrieve the quantity in stock for a given book.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Get Number of books by Id")
    @GetMapping("/number-of-books/{id}")
    public int getNoOfBooksById(@PathVariable Long id) {
        return bookStoreService.getNoOfBooksById(id);
    }

    /**
     * 5) List all books in the inventory.
     *
     * @return List<BookDto>
     */
    @ApiOperation(value = "List All Books")
    @GetMapping("/books-list")
    public List<BookDto> getAllBooks() {
        return bookStoreService.getAllBooks();
    }

    /**
     * 6) Search Books based author and filter by price range
     * @param author
     * @param lowPrice
     * @param higherPrice
     * @return List<BookDto>
     */
    @ApiOperation(value = "Get book by author, Filter by price range")
    @GetMapping("/author-price-range")
    public List<BookDto> getBookByAuthorFilterPrice(
            @RequestParam String author,
            @RequestParam BigDecimal lowPrice,
            @RequestParam BigDecimal higherPrice)
    {
        return bookStoreService.getBookByAuthorFilterPrice(author, lowPrice,higherPrice);
    }
}
