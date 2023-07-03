package com.fakeapi.FakeStore.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Hotel {
    private Long hotelId;
    private String name;
    private String address;
    private int roomCount;
//    public Hotel(Long hotelId, String name, String address, int roomCount){ -> @AllArgsConstructor
//        this.hoteldId = hotelId;
//    }
}
