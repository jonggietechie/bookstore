package com.demo.bookstore.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * Representation of Book Table
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    /**
     * Unique Book Number
     */
    @Id
    private Long id;

    /**
     * Title of the book
     */
    @NotNull
    private String title;

    /**
     * author of the book
     */
    private String author;

    /**
     * Price of the book
     */
    private BigDecimal price;

    /**
     * Amount of book in stock
     */
    @Min(value = 0, message = "Total Stock must be positive value.")
    private int quantity;
}
