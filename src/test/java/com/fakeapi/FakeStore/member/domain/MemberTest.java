package com.fakeapi.FakeStore.member.domain;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    private Address testAddress;
    private GeoLocation testGeoLocation;

    @BeforeEach
    void setUp() {
        testGeoLocation = new GeoLocation("37.5665", "126.9780");
        testAddress = new Address("Seoul", "123 Main St", 101, "12345", testGeoLocation);
    }

    @Test
    void 회원_생성_성공_모든_필드_제공() {
        Member member = new Member(
                "test@example.com",
                "testuser",
                "password123",
                "010-1234-5678",
                "Test Name",
                testAddress
        );

        assertNotNull(member);
        assertNull(member.getId());
        assertEquals("test@example.com", member.getEmail());
        assertEquals("testuser", member.getUsername());
        assertEquals("password123", member.getPassword());
        assertEquals("010-1234-5678", member.getPhone());
        assertEquals("Test Name", member.getName());
        assertEquals(testAddress, member.getAddress());
        assertTrue(member.getMemberRoles().isEmpty());
    }

    @Test
    void 회원_생성_성공_필수_필드만_제공() {
        Member member = new Member(
                "test@example.com",
                null,
                "password123",
                null,
                "Test Name",
                null
        );

        assertNotNull(member);
        assertEquals("test@example.com", member.getEmail());
        assertNull(member.getUsername());
        assertEquals("password123", member.getPassword());
        assertNull(member.getPhone());
        assertEquals("Test Name", member.getName());
        assertNull(member.getAddress());
    }

    @Test
    void Email이_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Member(null, "testuser", "password123", "010-1234-5678", "Test Name", testAddress);
        });
        assertEquals("Email cannot be null.", exception.getMessage());
    }

    @Test
    void Email이_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Member(" ", "testuser", "password123", "010-1234-5678", "Test Name", testAddress);
        });
        assertEquals("Email cannot be blank.", exception.getMessage());
    }

    @Test
    void Password가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Member("test@example.com", "testuser", null, "010-1234-5678", "Test Name", testAddress);
        });
        assertEquals("Password cannot be null.", exception.getMessage());
    }

    @Test
    void Password가_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Member("test@example.com", "testuser", " ", "010-1234-5678", "Test Name", testAddress);
        });
        assertEquals("Password cannot be blank.", exception.getMessage());
    }

    @Test
    void Name이_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Member("test@example.com", "testuser", "password123", "010-1234-5678", null, testAddress);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @Test
    void Name이_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Member("test@example.com", "testuser", "password123", "010-1234-5678", " ", testAddress);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void Username이_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Member("test@example.com", " ", "password123", "010-1234-5678", "Test Name", testAddress);
        });
        assertEquals("Username cannot be blank if present.", exception.getMessage());
    }

    @Test
    void Phone이_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Member("test@example.com", "testuser", "password123", " ", "Test Name", testAddress);
        });
        assertEquals("Phone cannot be blank if present.", exception.getMessage());
    }

    @Test
    void 역할_추가_메서드_테스트() {
        Member member = new Member("test@example.com", "testuser", "password123", "010-1234-5678", "Test Name", testAddress);
        Role role = new Role("ROLE_USER");

        member.addRole(role);

        assertEquals(1, member.getMemberRoles().size());
        MemberRole memberRole = member.getMemberRoles().iterator().next();
        assertEquals(member, memberRole.getMember());
        assertEquals(role, memberRole.getRole());
    }
}
