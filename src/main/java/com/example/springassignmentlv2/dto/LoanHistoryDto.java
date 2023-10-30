package com.example.springassignmentlv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LoanHistoryDto {
    private Long bookId;
    private Long memberId;
    private String memberName;
    private String memberPhoneNumber;
    private String bookTitle;
    private String bookAuthor;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private String returnStatus;
}
