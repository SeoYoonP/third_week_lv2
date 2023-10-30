package com.example.springassignmentlv2.controller;

import com.example.springassignmentlv2.dto.LoanRequestDto;
import com.example.springassignmentlv2.dto.LoanResponseDto;
import com.example.springassignmentlv2.dto.LoanReturnRequestDto;
import com.example.springassignmentlv2.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/{bookId}/loans")
    public ResponseEntity<?> loanBook(@PathVariable Long bookId, @RequestBody LoanRequestDto loanRequestDto) {
        try {
            LoanResponseDto loanResponseDto = loanService.loanBook(bookId, loanRequestDto.getMemberId());
            return new ResponseEntity<>(Map.of("status", "success", "data", loanResponseDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("status", "fail", "message", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{bookId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId, @RequestBody LoanReturnRequestDto loanReturnRequestDto) {
        try {
            loanService.returnBook(bookId, loanReturnRequestDto.getMemberId());
            return new ResponseEntity<>(Map.of("status", "success", "message", "도서 반납이 완료되었습니다."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("status", "fail", "message", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}