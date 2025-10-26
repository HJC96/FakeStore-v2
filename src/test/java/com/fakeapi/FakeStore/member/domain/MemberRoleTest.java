package com.fakeapi.FakeStore.member.domain;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberRoleTest {

    private Member testMember;
    private Role testRole;

    @BeforeEach
    void setUp() {
        GeoLocation testGeoLocation = new GeoLocation("37.5665", "126.9780");
        Address testAddress = new Address("Seoul", "123 Main St", 101, "12345", testGeoLocation);
        testMember = new Member("test@example.com", "testuser", "password123", "010-1234-5678", "Test Name", testAddress);
        testRole = new Role("ROLE_USER");
    }

    @Test
    void 회원_역할_생성_성공() {
        MemberRole memberRole = new MemberRole(testMember, testRole);

        assertNotNull(memberRole);
        assertNull(memberRole.getId()); // ID는 DB에서 자동 생성되므로 초기에는 null
        assertEquals(testMember, memberRole.getMember());
        assertEquals(testRole, memberRole.getRole());
    }

    @Test
    void Member가_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new MemberRole(null, testRole);
        });
        assertEquals("Member cannot be null.", exception.getMessage());
    }

    @Test
    void 역할이_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new MemberRole(testMember, null);
        });
        assertEquals("Role cannot be null.", exception.getMessage());
    }
}
