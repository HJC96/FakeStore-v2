package com.fakeapi.FakeStore.common.token.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "refreshtoken")
@NoArgsConstructor
@Setter
@Getter
public class RefreshToken {
    @Id
    @Column(name = "refreshtoken_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "refreshtoken_member_id")
    private Long memberId;
    @Column(name = "refreshtoken_value")
    private String value;
}
