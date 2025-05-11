package com.fakeapi.FakeStore.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;
    private String password;
    private String phone;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberRole> memberRoles = new HashSet<>();

    private String name;

    @Embedded
    private Address address;

    public void addRole(Role role) {
        MemberRole memberRole = new MemberRole(this, role);
        this.memberRoles.add(memberRole);
    }
}
