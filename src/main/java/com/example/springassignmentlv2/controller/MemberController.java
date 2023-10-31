package com.example.springassignmentlv2.controller;

import com.example.springassignmentlv2.dto.LoanHistoryDto;
import com.example.springassignmentlv2.dto.MemberRequestDto;
import com.example.springassignmentlv2.dto.MemberResponseDto;
import com.example.springassignmentlv2.entity.LoanRecord;
import com.example.springassignmentlv2.service.LoanService;
import com.example.springassignmentlv2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final LoanService loanService;

    @PostMapping("")
    public MemberResponseDto registerMember(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.registerMember(memberRequestDto);
    }

    /* 대출 내역 조회 조건 추가에 따른 API 증설
    * -한 회원의 모든 대출 내역 조회 */
    @GetMapping("/{memberId}/loans/all")
    public ResponseEntity<Map<String, Object>> getAllLoansByMember(@PathVariable Long memberId) {
        return getLoanHistoryResponse(memberId, true);
    }

    @GetMapping("/{memberId}/loans")
    public ResponseEntity<Map<String, Object>> getLoansByMember(@PathVariable Long memberId) {
        return getLoanHistoryResponse(memberId, false);
    }

    private ResponseEntity<Map<String, Object>> getLoanHistoryResponse(Long memberId, boolean isReturned) {
        try {
            List<LoanHistoryDto> loanHistoryList = memberService.getLoansByMember(memberId, isReturned);
            return new ResponseEntity<>(Map.of("status", "success", "data", loanHistoryList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("status", "fail", "message", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
