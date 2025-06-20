package com.cache.LibraryApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    private Integer id;
    private String name;
    private String genre;
    private LocalDate publishedDate;
    private String author;
    private Boolean isAvailable;
}
