package com.fakeapi.FakeStore.service;


import com.fakeapi.FakeStore.domain.Hotel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class HotelSearchService  {
    public Hotel getHotelById(Long hotelId){
        return new Hotel(hotelId, "The Continental", "1 Wall st, New York, NY 10005", 250);
    }
}