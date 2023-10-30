package com.example.springassignmentlv2.controller;

import com.example.springassignmentlv2.dto.MemberRequestDto;
import com.example.springassignmentlv2.dto.MemberResponseDto;
import com.example.springassignmentlv2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    public MemberResponseDto registerMember(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.registerMember(memberRequestDto);
    }
}
