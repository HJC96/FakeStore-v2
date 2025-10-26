package com.fakeapi.FakeStore.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Member(String email, String username, String password, String phone, String name, Address address) {
        this.email = Objects.requireNonNull(email, "Email cannot be null.");
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank.");
        }
        this.password = Objects.requireNonNull(password, "Password cannot be null.");
        if (password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank.");
        }
        this.name = Objects.requireNonNull(name, "Name cannot be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }

        // username, phone, address can be null for sign-up
        this.username = username;
        if (this.username != null && this.username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank if present.");
        }
        this.phone = phone;
        if (this.phone != null && this.phone.isBlank()) {
            throw new IllegalArgumentException("Phone cannot be blank if present.");
        }
        this.address = address;
        if (this.address != null) {
            new Address(address.getCity(), address.getStreet(), address.getNumber(), address.getZipcode(), address.getGeolocation());
        }
    }

    public void addRole(Role role) {
        MemberRole memberRole = new MemberRole(this, role);
        this.memberRoles.add(memberRole);
    }
}
