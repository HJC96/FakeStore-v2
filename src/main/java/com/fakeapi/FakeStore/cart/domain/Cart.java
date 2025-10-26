package com.fakeapi.FakeStore.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CART_DATE")
    private LocalDateTime date;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> products = new ArrayList<>();

    public Cart(Long userId, LocalDateTime date, List<CartItem> products) {
        Objects.requireNonNull(userId, "User ID cannot be null.");
        Objects.requireNonNull(date, "Date cannot be null.");
        this.userId = userId;
        this.date = date;
        this.products = products;
    }
}
