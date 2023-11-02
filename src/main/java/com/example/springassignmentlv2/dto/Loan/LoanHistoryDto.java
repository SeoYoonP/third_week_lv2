package com.example.springassignmentlv2.dto.Loan;

import com.example.springassignmentlv2.entity.Book;
import com.example.springassignmentlv2.entity.LoanRecord;
import com.example.springassignmentlv2.entity.Member;
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

    public LoanHistoryDto(LoanRecord loanRecord, Book book, Member member) {
        this.bookId = book.getId();
        this.memberId = member.getId();
        this.memberName = member.getName();
        this.memberPhoneNumber = member.getPhoneNumber();
        this.bookTitle = book.getTitle();
        this.bookAuthor = book.getAuthor();
        this.loanDate = loanRecord.getLoanDate();
        this.returnDate = loanRecord.getReturnDate();
        this.returnStatus = loanRecord.getIsReturned() ? "반납 완료" : "대출 중";
    }
}
