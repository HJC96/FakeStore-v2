package com.fakeapi.FakeStore.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToMany
    @JoinTable(
            name = "role", // 연결 테이블
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    private String name;

    @Embedded
    private Address address;

    public void addRole(Role role) {
        roles.add(role);
    }
}
