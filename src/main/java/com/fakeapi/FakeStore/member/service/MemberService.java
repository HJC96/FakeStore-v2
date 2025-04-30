package com.fakeapi.FakeStore.member.service;

import com.fakeapi.FakeStore.member.domain.Member;
import com.fakeapi.FakeStore.member.dto.request.MemberSignUpDTO;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
@Transactional
public interface MemberService {
    Member addMember(MemberSignUpDTO memberSignUpDTO);
    Optional<Member> findByEmail(String email);

    Optional<Member> getMember(Long memberId);

}
