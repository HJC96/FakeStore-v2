# FakeStore v2

이 프로젝트는 [FakeStore](https://github.com/HJC96/FakeStore)를 리팩토링한 백엔드 프로젝트입니다.

## 🛠 주요 개선 사항
### ✅ 환경 및 설정
- `application.properties` → `application.yml` 포맷으로 변경하여 가독성 향상
- Spring Boot 3.1.2 -> 3.4.5, JDK 17 -> 21
- `docker-compose`도입하여 MariaDB 컨테이너 기반 개발환경 구성
- Spring Boot 초기 실행 시 `data.sql`을 통한 데이터 자동 삽입 설정

### ✅ 프로젝트 구조 개선
- 기존 계층별 단순 디렉토리 구조 (`controller`, `service`, `repository` 등)에서, **도메인 중심 디렉토리 구조**로 개편
    - ex) `member/`, `product/`, `cart/` 하위에 각각 `controller`, `service`, `dto`, `domain`, `repository` 배치

### ✅ 리팩토링
- 사용하지 않는 코드 및 주석 제거
- Querydsl 사용 방식을 `QuerydslRepositorySupport` → `JPAQueryFactory` 주입 방식으로 전환


---
## ⚙️ 기술 스택
- Backend: Java 21, Spring Boot 3.4.5
- DB & ORM: MariaDB, JPA, QueryDSL
- Security: Spring Security, JWT
- API Docs: Swagger (SpringDoc OpenAPI)



## 🚀 실행 방법



