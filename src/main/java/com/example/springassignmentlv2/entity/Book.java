package com.example.springassignmentlv2.entity;

import com.example.springassignmentlv2.dto.book.BookRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String language;
    private String publisher;

    public Book(BookRequestDto bookRequestDto) {
        this.title = bookRequestDto.getTitle();
        this.author = bookRequestDto.getAuthor();
        this.language = bookRequestDto.getLanguage();
        this.publisher = bookRequestDto.getPublisher();
    }
}
