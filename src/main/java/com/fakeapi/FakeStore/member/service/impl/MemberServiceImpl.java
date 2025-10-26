package com.fakeapi.FakeStore.member.service.impl;

import com.fakeapi.FakeStore.common.security.exception.EmailAlreadyExistsException;
import com.fakeapi.FakeStore.member.domain.Member;
import com.fakeapi.FakeStore.member.domain.Role;
import com.fakeapi.FakeStore.member.dto.request.MemberSignUpDTO;
import com.fakeapi.FakeStore.member.repository.MemberRepository;
import com.fakeapi.FakeStore.member.repository.RoleRepository;
import com.fakeapi.FakeStore.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member addMember(MemberSignUpDTO memberSignUpDTO) {
        String email = memberSignUpDTO.getEmail();
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use: " + email);
        }

        Member member = new Member(
                memberSignUpDTO.getEmail(),
                null,
                passwordEncoder.encode(memberSignUpDTO.getPassword()),
                null,
                memberSignUpDTO.getName(),
                null
        );

        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        userRole.ifPresent(member::addRole);
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Optional<Member> getMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

