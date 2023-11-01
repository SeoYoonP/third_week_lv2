package com.example.springassignmentlv2.repository;

import com.example.springassignmentlv2.entity.Book;
import com.example.springassignmentlv2.entity.LoanRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRecordRepository extends JpaRepository<LoanRecord, Long> {
    Optional<LoanRecord> findByBookIdAndIsReturnedFalse(Long bookId);
    boolean existsByMemberIdAndIsReturnedFalse(Long memberId);
    Optional<LoanRecord> findByBookIdAndMemberIdAndIsReturnedFalse(Long bookId, Long memberId);
    List<LoanRecord> findByMemberIdOrderByLoanDateAsc(Long memberId);
    List<LoanRecord> findByMemberIdAndIsReturnedFalseOrderByLoanDateAsc(Long memberId);
}
