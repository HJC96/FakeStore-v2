package com.fakeapi.FakeStore.controller;

import com.fakeapi.FakeStore.domain.Hotel;
import com.fakeapi.FakeStore.service.HotelSearchService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 스프링 빈으로 생성
@AllArgsConstructor

public class ApiController {
    private HotelSearchService hotelSearchService;

    @ResponseBody
    @RequestMapping(method= RequestMethod.GET, path="/hotels/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") Long hotelId){
        Hotel hotel = hotelSearchService.getHotelById(hotelId);
        return ResponseEntity.ok(hotel);
    }
}
