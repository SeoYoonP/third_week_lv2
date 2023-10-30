package com.example.springassignmentlv2.service;

import com.example.springassignmentlv2.dto.LoanHistoryDto;
import com.example.springassignmentlv2.dto.MemberRequestDto;
import com.example.springassignmentlv2.dto.MemberResponseDto;
import com.example.springassignmentlv2.entity.Book;
import com.example.springassignmentlv2.entity.LoanRecord;
import com.example.springassignmentlv2.entity.Member;
import com.example.springassignmentlv2.repository.BookRepository;
import com.example.springassignmentlv2.repository.LoanRecordRepository;
import com.example.springassignmentlv2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sound.midi.MetaMessage;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final LoanRecordRepository loanRecordRepository;
    private final BookRepository bookRepository;

    public MemberResponseDto registerMember(MemberRequestDto memberRequestDto) {
        Member member = new Member(memberRequestDto);
        memberRepository.save(member);
        return new MemberResponseDto(member);
    }

    public List<LoanHistoryDto> getLoansByMember(Long memberId) {
        List<LoanRecord> loanRecords = loanRecordRepository.findByMemberIdOrderByLoanDateAsc(memberId);
        return loanRecords.stream().map(loanRecord -> {
            Member member = memberRepository.findById(loanRecord.getMemberId())
                    .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다"));
            Book book = bookRepository.findById(loanRecord.getMemberId())
                    .orElseThrow(() -> new IllegalArgumentException("도서 정보를 찾을 수 없습니다"));

            String returnStatus = loanRecord.getIsReturned() ? "반납 완료" : "대출 중";

            return new LoanHistoryDto(
                    book.getId(),
                    member.getId(),
                    member.getName(),
                    member.getPhoneNumber(),
                    book.getTitle(),
                    book.getAuthor(),
                    loanRecord.getLoanDate(),
                    loanRecord.getReturnDate(),
                    returnStatus
            );
        }).collect(Collectors.toList());
    }
}
