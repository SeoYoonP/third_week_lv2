package com.example.springassignmentlv2.controller;

import com.example.springassignmentlv2.dto.book.BookRequestDto;
import com.example.springassignmentlv2.dto.book.BookResponseDto;
import com.example.springassignmentlv2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @PostMapping("")
    public BookResponseDto bookRegistration(@RequestBody BookRequestDto bookRequestDto) {
        return bookService.bookRegistration(bookRequestDto);
    }

    @GetMapping("")
    public List<BookResponseDto> getBookList() {
        return bookService.getBookList();
    }

    @GetMapping("/{bookId}")
    public BookResponseDto getBook(@PathVariable Long bookId) {
        return bookService.getBook(bookId);
    }
}
