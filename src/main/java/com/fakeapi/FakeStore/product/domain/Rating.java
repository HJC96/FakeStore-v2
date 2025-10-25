package com.fakeapi.FakeStore.product.domain;

import lombok.*;

import jakarta.persistence.Embeddable;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Rating {
    private Double rate;
    private Integer count;

    public void validate() {
        if (rate == null) {
            throw new IllegalArgumentException("Rating rate cannot be null.");
        }
        if (rate < 0) {
            throw new IllegalArgumentException("Rating rate must be zero or positive.");
        }
        if (count == null) {
            throw new IllegalArgumentException("Rating count cannot be null.");
        }
        if (count < 0) {
            throw new IllegalArgumentException("Rating count must be zero or positive.");
        }
    }
}
