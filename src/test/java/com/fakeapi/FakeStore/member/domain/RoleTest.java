package com.fakeapi.FakeStore.member.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void 역할_생성_성공() {
        Role role = new Role("ROLE_USER");

        assertNotNull(role);
        assertNull(role.getId()); // ID는 DB에서 자동 생성되므로 초기에는 null
        assertEquals("ROLE_USER", role.getName());
    }

    @Test
    void 역할_이름이_null일_경우_예외_발생() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Role(null);
        });
        assertEquals("Role name cannot be null.", exception.getMessage());
    }

    @Test
    void 역할_이름이_빈_문자열일_경우_예외_발생() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Role(" ");
        });
        assertEquals("Role name cannot be blank.", exception.getMessage());
    }
}
