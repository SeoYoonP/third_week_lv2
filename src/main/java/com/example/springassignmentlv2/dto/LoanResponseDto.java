package com.example.springassignmentlv2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoanResponseDto {
    private Long loanId;
    private Long bookId;
    private Long memberId;
    private LocalDateTime loanDate;
    private boolean isReturned;

}
