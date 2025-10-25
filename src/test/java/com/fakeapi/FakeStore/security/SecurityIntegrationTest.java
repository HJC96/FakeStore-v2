package com.fakeapi.FakeStore.security;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SecurityIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    private String getAuthToken() {
        // 실제 존재하는 사용자로 로그인하여 토큰을 발급받습니다.
        // data.sql에 정의된 사용자 정보를 사용합니다.
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"john@gmail.com\", \"password\": \"0000\"}")
                .when()
                .post("/members/login")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("accessToken", notNullValue())
                .extract().response();

        return "Bearer " + response.jsonPath().getString("accessToken");
    }


    @Test
    void 인증없이_보호된_API_접근시_401_에러가_발생한다() {
        given()
                .when()
                .get("/members/info")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void 유효한_토큰으로_보호된_API_접근시_성공한다() {
        String authToken = getAuthToken();

        given()
                .header("Authorization", authToken)
                .when()
                .get("/members/info")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void 유효하지_않은_토큰으로_보호된_API_접근시_401_에러가_발생한다() {
        String invalidToken = "Bearer invalid-token";

        given()
                .header("Authorization", invalidToken)
                .when()
                .get("/members/info")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
}
