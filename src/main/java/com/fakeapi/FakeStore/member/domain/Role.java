package com.fakeapi.FakeStore.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "role")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

    public Role(String name) {
        this.name = Objects.requireNonNull(name, "Role name cannot be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Role name cannot be blank.");
        }
    }
}
