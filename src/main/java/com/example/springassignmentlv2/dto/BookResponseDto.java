package com.example.springassignmentlv2.dto;

import com.example.springassignmentlv2.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String language;
    private String publisher;
    private LocalDateTime registrationDate;

    public BookResponseDto(Book books) {
        this.id = books.getId();
        this.title = books.getTitle();
        this.author = books.getAuthor();
        this.language = books.getLanguage();
        this.publisher = books.getPublisher();
        this.registrationDate = books.getRegistrationDate();
    }
}
