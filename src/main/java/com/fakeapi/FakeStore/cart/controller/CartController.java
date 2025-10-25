package com.fakeapi.FakeStore.cart.controller;

import com.fakeapi.FakeStore.cart.domain.Cart;
import com.fakeapi.FakeStore.cart.dto.CartDTO;
import com.fakeapi.FakeStore.cart.service.CartService;
import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.fakeapi.FakeStore.common.dto.PageResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SecurityRequirement(name = "bearerAuth")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public CartDTO read(@PathVariable("id") Long id) {
        log.info("read id: " + id);
        return cartService.read(id);
    }

    @GetMapping
    public PageResponseDTO<CartDTO> listCarts(
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
            @RequestParam(value = "startdate", required = false) String start,
            @RequestParam(value = "enddate", required = false) String end,
            PageRequestDTO pageRequestDTO) {

        // 정렬 설정
        pageRequestDTO.setSort(sort);

        // 날짜 필터링
        if (start != null && end != null) {
            LocalDateTime startDate = LocalDate.parse(start).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(end).plusDays(1).atStartOfDay(); // inclusive
            return cartService.listWithDateRange(pageRequestDTO, startDate, endDate);
        }

        // 제한 개수 필터링
        if (limit != null && limit > 0) {
            return cartService.listWithLimitCart(pageRequestDTO, limit);
        }

        return cartService.list(pageRequestDTO);
    }

    @PostMapping
    public ResponseEntity<Cart> registerCart(@RequestBody @Valid CartDTO cartDTO) {
        Cart registeredCart = cartService.register(cartDTO);
        return new ResponseEntity<>(registeredCart, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // PUT은 일반적으로 리소스 전체를 업데이트 하는데 사용
    public CartDTO updatePut(@PathVariable("id") Long id, @RequestBody @Valid CartDTO cartDTO) {
        return cartService.update(id, cartDTO);
    }

    @PatchMapping("{id}") // PATCH는 일반적으로 리소스 일부를 업데이트 하는데 사용
    public CartDTO updatePatch(@PathVariable("id") Long id, @RequestBody @Valid CartDTO cartDTO) {
        return cartService.update(id, cartDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        cartService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/user/{id}")
    public List<CartDTO> readUserCart(@PathVariable("id") Long id) {

        return cartService.readUserCart(id);
    }
}
