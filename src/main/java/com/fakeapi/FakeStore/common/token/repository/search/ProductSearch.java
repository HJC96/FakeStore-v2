package com.fakeapi.FakeStore.common.token.repository.search;

import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.fakeapi.FakeStore.product.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductSearch {
    Page<ProductDTO> list(PageRequestDTO pageRequestDTO);
}
