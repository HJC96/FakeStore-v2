package com.fakeapi.FakeStore.common.token.repository.search;


import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.fakeapi.FakeStore.product.domain.QCategory;
import com.fakeapi.FakeStore.product.domain.QProduct;
import com.fakeapi.FakeStore.product.dto.ProductDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@RequiredArgsConstructor
public class ProductSearchImpl implements ProductSearch {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductDTO> list(PageRequestDTO pageRequestDTO) {

        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        JPQLQuery<ProductDTO> dtoQuery = queryFactory
                .select(Projections.bean(ProductDTO.class,
                        product.id,
                        product.title,
                        product.price,
                        product.description,
                        category.name.as("category"),
                        product.image,
                        product.rating
                ))
                .from(product)
                .leftJoin(product.category, category)
                .offset(pageRequestDTO.getPageable("id").getOffset())
                .limit(pageRequestDTO.getPageable("id").getPageSize());

        List<ProductDTO> list = dtoQuery.fetch();

        long count = queryFactory
                .select(product.count())
                .from(product)
                .fetchOne();

        return new PageImpl<>(list, pageRequestDTO.getPageable("id"), count);
    }
}

