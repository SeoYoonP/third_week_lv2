package com.example.springassignmentlv2.entity;

import com.example.springassignmentlv2.dto.MemberRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    @Column(unique = true)
    private String personalId;
    @Column(unique = true)
    private String phoneNumber;
    private String address;
    private LocalDateTime penaltyExpirationDate;

    public Member(MemberRequestDto memberRequestDto) {
        this.name = memberRequestDto.getName();
        this.gender = memberRequestDto.getGender();
        this.personalId = memberRequestDto.getPersonalId();
        this.phoneNumber = memberRequestDto.getPhoneNumber();
        this.address = memberRequestDto.getAddress();
    }
    public void  setPenaltyExpirationDate(LocalDateTime penaltyExpirationDate){
        this.penaltyExpirationDate = penaltyExpirationDate;
    }
}
