package com.example.springassignmentlv2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_records")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private Long memberId;
    private Boolean isReturned;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
}
