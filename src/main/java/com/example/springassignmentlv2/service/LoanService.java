package com.example.springassignmentlv2.service;

import com.example.springassignmentlv2.dto.LoanResponseDto;
import com.example.springassignmentlv2.entity.Book;
import com.example.springassignmentlv2.entity.LoanRecord;
import com.example.springassignmentlv2.entity.Member;
import com.example.springassignmentlv2.repository.BookRepository;
import com.example.springassignmentlv2.repository.LoanRecordRepository;
import com.example.springassignmentlv2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRecordRepository loanRecordRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    public LoanResponseDto loanBook(Long bookId, Long memberId) {
        boolean hasReturnedLoanBookOrNot = loanRecordRepository.existsByMemberIdAndIsReturnedFalse(memberId);
        if (hasReturnedLoanBookOrNot) {
            throw new IllegalStateException("먼저 대출하신 도서를 반납 후 이용 부탁드립니다.");
        }
        Optional<LoanRecord> existingLoanBookOrNot = loanRecordRepository.findByBookIdAndIsReturnedFalse(bookId);
        if (existingLoanBookOrNot.isPresent()) {
            throw new IllegalStateException("해당 도서는 대출 중입니다.");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 도서ID가 존재하지 않습니다"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원ID는 등록되지 않은 ID입니다"));

        LoanRecord newLoan = new LoanRecord();
        newLoan.setBookId(bookId);
        newLoan.setMemberId(memberId);
        newLoan.setIsReturned(false);
        newLoan.setLoanDate(LocalDateTime.now());
        loanRecordRepository.save(newLoan);

        LoanResponseDto loanResponseDto = new LoanResponseDto();
        loanResponseDto.setLoanId(newLoan.getId());
        loanResponseDto.setBookId(bookId);
        loanResponseDto.setMemberId(memberId);
        loanResponseDto.setLoanDate(newLoan.getLoanDate());

        return loanResponseDto;
    }

    public void returnBook(Long bookId, Long memberId) {
        LoanRecord loanRecord = loanRecordRepository.findByBookIdAndMemberIdAndIsReturnedFalse(bookId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("관리자에게 문의하세요."));

        loanRecord.setIsReturned(true);
        loanRecord.setReturnDate(LocalDateTime.now());

        if (loanRecord.getLoanDate().plusDays(7).isBefore(loanRecord.getReturnDate())){
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException(("해당 회원을 찾을 수 없습니다")));
            member.setPenaltyExpirationDate(LocalDateTime.now().plusWeeks(2));
            memberRepository.save(member);
        }

        loanRecordRepository.save(loanRecord);
    }
}
