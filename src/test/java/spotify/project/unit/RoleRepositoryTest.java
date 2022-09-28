package spotify.project.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spotify.project.models.Role;
import spotify.project.repositories.CityRepository;
import spotify.project.repositories.RoleRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static spotify.project.mocks.MockedRoles.getMockedRoles;

@DataJpaTest
public class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepositoryTest;

    @BeforeEach
    public void tearDown(){
        roleRepositoryTest.deleteAll();
    }

    @Test
    public void get_Roles_by_RoleType_Should_Succeed(){

        // Given

        List<Role> roleList = getMockedRoles();
        Role role = roleList.get(2);

        roleRepositoryTest.save(roleList.get(0));
        roleRepositoryTest.save(roleList.get(1));

        // When

        List<Role> response = roleRepositoryTest.findByRoleType("USER").stream().toList();

        final Executable action = () -> roleRepositoryTest.findByRoleType(role.getRoleType()).get();

        List<Role> expected = List.of(roleList.get(0));

        // Then

        assertThrows(NoSuchElementException.class, action);
        assertNotNull(response);
        assertEquals(expected, response);
    }


}
