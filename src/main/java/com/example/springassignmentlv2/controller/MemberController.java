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

    @GetMapping("/{memberId}/loans")
    public ResponseEntity<?> getLoansByMember(@PathVariable Long memberId) {
        try {
            List<LoanHistoryDto> loanHistoryList = memberService.getLoansByMember(memberId);
            return new ResponseEntity<>(Map.of("status", "success", "data", loanHistoryList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("status", "fail", "message", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
