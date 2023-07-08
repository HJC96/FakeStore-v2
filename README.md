# API 서버를 만들면서 기록한 내용들 입니다.

- 개발언어: 자바
- IDE: IntellJ(Community Edition)
- 프로젝트 SDK: JDK 11 이상
- 의존성 관리 툴: Maven

1. 환경 세팅(기본 프로젝트 셋팅시)

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.fakeapi.example</groupId>
    <artifactId>FakeAPI</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

1. ERD 설계

<img width="1224" alt="image" src="https://github.com/HJC96/FakeStore/assets/87226129/d0d33252-50ea-417f-9427-08b748e4cc7e">

<img width="625" alt="image" src="https://github.com/HJC96/FakeStore/assets/87226129/ebbefac4-7607-446d-994d-88705d3f5aa9">

1. domain 작성

```java
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    private String description;

    private String category;

//    private ImageSet image;
//  rating -> json 객체인데 어떻게 표현해야할지 모르겠음...{}
}
```

1. repo 작성
    1. jpa연동
    
    ```java
    public interface ProductRepository extends JpaRepository<Product, Long> {
    }
    ```
    
    1. db테이블 작성
    
    ```sql
    create table fakestore(
    id BIGINT auto_increment primary key,
    title varchar(100) not null,
    price DOUBLE
    );
    ```
    
    <img width="513" alt="image" src="https://github.com/HJC96/FakeStore/assets/87226129/96b83285-d4e8-47b5-af4a-30f541183e9b">

    
    *USER, HOST 확인
    
    ```bash
    SELECT User, Host From mysql.user;
    ```
    
    <img width="539" alt="image" src="https://github.com/HJC96/FakeStore/assets/87226129/e94b23c5-4ad7-4197-be29-fc139a6305ec">

    
    mysql - user 생성(**어떤 특정 디비에 들어가지 말고 none인 상태에서 할것!**!!)
    
    ```bash
    CREATE USER 'newuser'@'%' IDENTIFIED BY 'password';
    ```
    
    <img width="543" alt="image" src="https://github.com/HJC96/FakeStore/assets/87226129/e7435b92-e58f-414e-8324-b6abd9fb15fc">

    
    권한 부여
    
    ```bash
    GRANT ALL PRIVILEGES ON fakestore.* TO 'storeuser'@'localhost';
    ```
    
    <img width="534" alt="image" src="https://github.com/HJC96/FakeStore/assets/87226129/af5021e1-7e82-41c8-9111-aa88b526099b">

    
    1. 테스트코드
    
    - 오류 발생
        - 테스트 코드 에러 db에 JPA로 쏴주는게 안됨…/
        - 네이버 블로그에 트러블 슈팅 작성 완료
    
2. service 작성

```java
package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.domain.Product;

public interface FakeStoreService {
    Product read(Long id);
}
```

```java
package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FakeStoreServiceImpl implements FakeStoreService{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public Product read(Long id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = result.orElseThrow();
        return modelMapper.map(todo, TodoDTO.class);
    }
}
```

- modelMapper 등록 필요
    - modelMapper란?
        - 엔티티 객체 ↔ DTO 도와주는 라이브러리

**pom.xml**

```java
<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>3.0.0</version>
</dependency>
```

**config 디렉토리에 RootConfig.java 추가**

```java
package com.fakeapi.FakeStore.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {

    @Bean
    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }
}
```

**DTO/ProductDTO.java 작성**

```java
package com.fakeapi.FakeStore.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor

@Data
//@Getter
//@Setter
//@ToString
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private String title;
}
```

ProductService 작성

```java
package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.dto.ProductDTO;

public interface ProductService {
    ProductDTO read(Long id);
}
```

ProductServiceImpl 작성

```java
package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.domain.Product;
import com.fakeapi.FakeStore.dto.ProductDTO;
import com.fakeapi.FakeStore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

//    public FakeStoreServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
//        this.productRepository = productRepository;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public ProductDTO read(Long id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = result.orElseThrow();
        return modelMapper.map(product, ProductDTO.class);
    }
}
```

Service TEST코드 작성

```java
package com.fakeapi.FakeStore.service;

import com.fakeapi.FakeStore.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class ProductServiceTests {
    @Autowired
    ProductServiceImpl productServiceImpl;
    @Test
    public void ProductServiceTest(){
        ProductDTO productDTO =productServiceImpl.read(2L);
        log.info(productDTO);

    }
}
```

1. controller 작성

ProductController

```java
package com.fakeapi.FakeStore.controller;

import com.fakeapi.FakeStore.dto.ProductDTO;
import com.fakeapi.FakeStore.service.ProductService;
import com.fakeapi.FakeStore.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/products") 
@RequiredArgsConstructor // final or @NotNull이 붙은 필드의 생성자를 생성해주는 어노테이션
public class ProductController {

    private final ProductService productService;
    @GetMapping("/{id}")
    public ProductDTO read(@PathVariable("id") Long id){
        log.info("read id: "+ id);
        return productService.read(id);
    }

}
```

- 테스트

<img width="561" alt="image" src="https://github.com/HJC96/FakeStore/assets/87226129/6381bcee-c23d-4aee-a571-db70a844b708">

