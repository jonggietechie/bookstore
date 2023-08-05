package com.demo.bookstore.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long>{
    @Query(value = "SELECT * from book b WHERE " +
            "(LOWER(b.author) like %?1%) " +
            "AND (b.price BETWEEN ?2 AND ?3)",
            nativeQuery = true)
    List<Book> findAllBookByCAuthorFilterPrice(String author, BigDecimal lowerPrice, BigDecimal higherPrice);
}
