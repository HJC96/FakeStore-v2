package com.fakeapi.FakeStore.domain;

import lombok.*;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Rating {
    private Double rate;
    private Integer count;
}
