package com.example.springassignmentlv2.dto.member;

import com.example.springassignmentlv2.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.gender = member.getGender();
        this.phoneNumber = member.getPhoneNumber();
        this.address = member.getAddress();
    }
}
