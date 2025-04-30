package com.fakeapi.FakeStore.product.domain;

import lombok.*;

import jakarta.persistence.Embeddable;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Rating {
    private Double rate;
    private Integer count;
}
