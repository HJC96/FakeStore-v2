package com.fakeapi.FakeStore.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating {
    private Double rate;
    private Integer count;

    public Rating(Double rate, Integer count) {
        this.rate = Objects.requireNonNull(rate, "Rating rate cannot be null.");
        this.count = Objects.requireNonNull(count, "Rating count cannot be null.");
        if (rate < 0) {
            throw new IllegalArgumentException("Rating rate must be zero or positive.");
        }
        if (count < 0) {
            throw new IllegalArgumentException("Rating count must be zero or positive.");
        }
    }
}
