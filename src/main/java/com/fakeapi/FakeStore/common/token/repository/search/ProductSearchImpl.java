package com.fakeapi.FakeStore.common.token.repository.search;




import com.fakeapi.FakeStore.product.domain.Product;
import com.fakeapi.FakeStore.product.domain.QCategory;
import com.fakeapi.FakeStore.product.domain.QProduct;
import com.querydsl.core.types.Projections;

import com.querydsl.jpa.JPQLQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.fakeapi.FakeStore.common.dto.PageRequestDTO;
import com.fakeapi.FakeStore.product.dto.ProductDTO;

import java.util.List;

public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {
    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public Page<ProductDTO> list(PageRequestDTO pageRequestDTO) {

        QProduct product = QProduct.product;
        QCategory category = QCategory.category; // Category에 대한 Querydsl 객체 생성

        JPQLQuery<Product> query = from(product).leftJoin(product.category, category);
        //paging
        this.getQuerydsl().applyPagination(pageRequestDTO.getPageable("id"), query);

        JPQLQuery<ProductDTO> dtoQuery = query.select(Projections.bean(ProductDTO.class,
                product.id,
                product.title,
                product.price,
                product.description,
                category.name.as("category"), // Category의 name을 category로 매핑
                product.image,
                product.rating
        ));


        List<ProductDTO> list = dtoQuery.fetch();
        long count = dtoQuery.fetchCount();
        return new PageImpl<>(list, pageRequestDTO.getPageable("id"), count);
    }
}

