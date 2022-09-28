package spotify.project.mocks;

import spotify.project.models.Role;

import java.util.List;

public class MockedRoles {

    public static Role getMockedRole3(){
        return Role.builder()
                .id(3L)
                .roleType("USER")
                .build();

    }

    public static Role getMockedRole2(){
        return Role.builder()
                .id(2L)
                .roleType("ADMIN")
                .build();

    }

    public static Role getMockedRole1(){
        return Role.builder()
                .id(1L)
                .roleType("OWNER")
                .build();

    }

    public static List<Role> getMockedRoles(){
        return List.of(
                Role.builder()
                        .id(1L)
                        .roleType("USER")
                        .build(),
                Role.builder()
                        .id(2L)
                        .roleType("ADMIN")
                        .build(),
                Role.builder()
                        .id(3L)
                        .roleType("OWNER")
                        .build());

    }
}
