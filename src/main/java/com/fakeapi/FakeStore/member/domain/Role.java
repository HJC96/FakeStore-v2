package com.fakeapi.FakeStore.member.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "role")
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;
}
