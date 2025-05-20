# FakeStore-v2

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

✅ 인증 및 보안 개선
- 기존 JWT 인증 방식에 Redis 기반 블랙리스트 기능을 추가하여 로그아웃 시 토큰 무효화 가능
- Redis에 로그아웃된 Access Token을 저장하고, 매 요청마다 필터에서 블랙리스트 확인
~~~
[Client] → 로그인 → [Spring Security] → JWT 발급
                                      ↓
[Client] → 매 요청마다 JWT 포함 → 인증 필터
                                └→ Redis 블랙리스트 조회
                                      ↓
                        [없으면] → 다음 필터
                        [있으면] → 401 Unauthorized

[Client] → 로그아웃 요청 → Redis에 토큰 저장 (TTL=만료까지 남은 시간)
~~~
- Redis를 활용한 로그인 시도 제한 기능 추가: 특정 이메일 기준으로 로그인 실패 횟수를 기록하고, 5회 이상 실패 시 15분간 로그인 차단

### ✅ 리팩토링
- 사용하지 않는 코드 및 주석 제거
- Querydsl 사용 방식을 `QuerydslRepositorySupport` → `JPAQueryFactory` 주입 방식으로 전환

---
## ⚙️ 기술 스택
- Backend: Java 21, Spring Boot 3.4.5
- DB & ORM: MariaDB, JPA, QueryDSL, Redis
- Security: Spring Security, JWT
- API Docs: Swagger (SpringDoc OpenAPI)


