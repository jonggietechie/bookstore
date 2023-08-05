package com.demo.bookstore.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    /**
     * Unique Book Number
     */
    @ApiModelProperty(value="Book Unique Id")
    private Long id;

    /**
     * Title of the book
     */
    @ApiModelProperty(value="Title of the book")
    private String title;

    /**
     * author of the book
     */
    @ApiModelProperty(value="Author of the book")
    private String author;

    /**
     * Price of the book
     */
    @ApiModelProperty(value="Price of the book")
    private BigDecimal price;

    /**
     * Amount of book in stock
     */
    @ApiModelProperty(value="Amount of book in stock")
    private int quantity;
}
