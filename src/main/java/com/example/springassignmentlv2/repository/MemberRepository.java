package com.example.springassignmentlv2.repository;

import com.example.springassignmentlv2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByPersonalId(String personalId);
    boolean existsByPhoneNumber(String phoneNumber);
}
