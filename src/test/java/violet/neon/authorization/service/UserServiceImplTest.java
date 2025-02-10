package violet.neon.authorization.service;

import org.junit.jupiter.api.Test;
import violet.neon.authorization.model.User;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceImplTest {
    private final UserService out = new UserServiceImpl();
    private final User mockUser1 = new User();
    private final User mockUser2 = new User();

    {
        mockUser1.setId(1);
        mockUser1.setFullName("Ivan Ivanovich Ivanov");
        mockUser1.setDepartment(1);

        mockUser1.setId(2);
        mockUser2.setFullName("Petr Petrovich Petrov");
        mockUser2.setDepartment(2);
    }

    @Test
    void shouldAddUser_ThenReturnThatUser() {
        User result = out.addUser(mockUser1);
        Collection<User> allUsers = out.getAllUsers();

        assertThat(result).isEqualTo(mockUser1);
        assertThat(allUsers).contains(mockUser1);
        assertThat(allUsers).hasSize(1);
    }

    @Test
    void shouldFindUserById_ThenReturnThatUser() {
        User expected = out.addUser(mockUser1);
        User result = out.findUser(expected.getId());

        assertThat(result).isEqualTo(mockUser1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldUpdateUser_WhenUserExists_ThenReturnThatUser() {
        User mockUser3 = out.addUser(mockUser1);
        mockUser3.setDepartment(3);
        mockUser3.setFullName("Sidr Sidorovich Sidorov");
        User result = out.updateUser(mockUser3);

        assertThat(result).isEqualTo(mockUser3);
    }

    @Test
    void shouldUpdateUser_WhenUserNotExists_ThenReturnNull() {
        User result = out.updateUser(mockUser1);

        assertThat(result).isNull();
    }

    @Test
    void shouldDeleteUser_ThenReturnThatUser() {
        User expected = out.addUser(mockUser1);
        User result = out.deleteUser(expected.getId());
        Collection<User> allUsers = out.getAllUsers();

        assertThat(result).isEqualTo(mockUser1);
        assertThat(result).isEqualTo(expected);
        assertThat(allUsers).hasSize(0);
    }

    @Test
    void shouldReturnAllUsers_ThenReturnTheseAllUsers() {
        User result1 = out.addUser(mockUser1);
        User result2 = out.addUser(mockUser2);
        Collection<User> allUsers = out.getAllUsers();

        assertThat(result1).isEqualTo(mockUser1);
        assertThat(result2).isEqualTo(mockUser2);
        assertThat(allUsers).hasSize(2);
    }
}