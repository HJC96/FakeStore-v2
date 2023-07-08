package com.fakeapi.FakeStore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Getter @Setter
public class Rating {
    private double rate;
    private int count;
}
