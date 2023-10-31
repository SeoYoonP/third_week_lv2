package com.example.springassignmentlv2.service;

import com.example.springassignmentlv2.dto.book.BookRequestDto;
import com.example.springassignmentlv2.dto.book.BookResponseDto;
import com.example.springassignmentlv2.entity.Book;
import com.example.springassignmentlv2.repository.BookRepository;
import com.example.springassignmentlv2.repository.LoanRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final LoanRecordRepository loanRecordRepository;

    public BookResponseDto bookRegistration(BookRequestDto bookRequestDto) {
        Book book = new Book(bookRequestDto);
        book = bookRepository.save(book);
        boolean isAvailable = isBookAvailableForLoan(book.getId());
        return new BookResponseDto(book, isAvailable);
    }

    public List<BookResponseDto> getBookList() {
        return bookRepository.findAllByOrderByRegistrationDateDesc()
                .stream()
                .map(book -> new BookResponseDto(book, isBookAvailableForLoan(book.getId())))
                .collect(Collectors.toList());
    }

    public BookResponseDto getBook(Long bookId) {
        Book book = findBook(bookId);
        boolean isAvailable = isBookAvailableForLoan(bookId);
        return new BookResponseDto(book, isAvailable);
    }

    private Book findBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 책입니다."));
    }

    private boolean isBookAvailableForLoan(Long bookId) {
        return loanRecordRepository.findByBookIdAndIsReturnedFalse(bookId).isEmpty();
    }
}
