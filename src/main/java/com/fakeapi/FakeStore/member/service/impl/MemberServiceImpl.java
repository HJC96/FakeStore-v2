package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.common.security.exception.EmailAlreadyExistsException;
import com.fakeapi.FakeStore.member.domain.Member;
import com.fakeapi.FakeStore.member.domain.Role;
import com.fakeapi.FakeStore.member.dto.request.MemberSignUpDTO;
import com.fakeapi.FakeStore.member.repository.MemberRepository;
import com.fakeapi.FakeStore.member.repository.RoleRepository;
import com.fakeapi.FakeStore.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
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
        Member member = new Member();
        log.info(memberSignUpDTO.getName());
        log.info(memberSignUpDTO.getEmail());
        member.setName(memberSignUpDTO.getName());
        member.setEmail(memberSignUpDTO.getEmail());
        member.setPassword(passwordEncoder.encode(memberSignUpDTO.getPassword()));

        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        member.addRole(userRole.get());
        Member saveMember = memberRepository.save(member);
        return saveMember;
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
