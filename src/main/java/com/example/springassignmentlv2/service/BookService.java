package com.example.springassignmentlv2.service;

import com.example.springassignmentlv2.dto.BookRequestDto;
import com.example.springassignmentlv2.dto.BookResponseDto;
import com.example.springassignmentlv2.entity.Book;
import com.example.springassignmentlv2.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookResponseDto bookRegistration(BookRequestDto bookRequestDto) {
        Book book = new Book(bookRequestDto);
        book = bookRepository.save(book);
        return new BookResponseDto(book);
    }

    public List<BookResponseDto> getBookList() {
        return bookRepository.findAllByOrderByRegistrationDateDesc()
                .stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
    }

    public BookResponseDto getBook(Long bookId) {
        Book book = findBook(bookId);
        return new BookResponseDto(book);
    }

    private Book findBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 책입니다."));
    }
}
