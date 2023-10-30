package com.example.springassignmentlv2.service;

import com.example.springassignmentlv2.dto.MemberRequestDto;
import com.example.springassignmentlv2.dto.MemberResponseDto;
import com.example.springassignmentlv2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberResponseDto registerMember(MemberRequestDto memberRequestDto) {
        return null;
    }
}
