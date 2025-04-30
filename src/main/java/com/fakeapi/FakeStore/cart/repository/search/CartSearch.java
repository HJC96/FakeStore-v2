package com.fakeapi.FakeStore.cart.repository.search;

import com.fakeapi.FakeStore.cart.dto.CartDTO;
import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface CartSearch {
    Page<CartDTO> list(PageRequestDTO pageRequestDTO);
}
