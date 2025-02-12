package violet.neon.authorization.service;

import org.junit.jupiter.api.Test;
import violet.neon.authorization.exception.UserNotFoundException;
import violet.neon.authorization.model.User;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class UserServiceImplTest {
    private final UserService out = new UserServiceImpl();
    private final User mockUser1 = new User();
    private final User mockUser2 = new User();

    {
        mockUser1.setFullName("Ivan Ivanovich Ivanov");
        mockUser1.setDepartment(1);

        mockUser2.setFullName("Petr Petrovich Petrov");
        mockUser2.setDepartment(2);
    }

    @Test
    void shouldAddUser_ThenReturnThatUser() {
        User result = out.addUser(mockUser1);
        Collection<User> allUsers = out.getAllUsers();
        mockUser1.setId(result.getId());

        assertThat(result).isEqualTo(mockUser1);
        assertThat(allUsers).contains(mockUser1);
        assertThat(allUsers).hasSize(1);
    }

    @Test
    void shouldFindUserById_ThenReturnThatUser() {
        User expected = out.addUser(mockUser1);
        User result = out.findUser(expected.getId());
        mockUser1.setId(expected.getId());

        assertThat(result).isEqualTo(mockUser1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldFindUserById_WhenUserNotExists_ThenThrowUserNotFoundException() {
        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() -> out.findUser(mockUser1.getId()));
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
    void shouldUpdateUser_WhenUserNotExists_ThenThrowUserNotFoundException() {
        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() -> out.updateUser(mockUser1));
    }

    @Test
    void shouldDeleteUser_ThenReturnThatUser() {
        User expected = out.addUser(mockUser1);
        mockUser1.setId(expected.getId());
        User result = out.deleteUser(expected.getId());
        Collection<User> allUsers = out.getAllUsers();

        assertThat(result).isEqualTo(mockUser1);
        assertThat(result).isEqualTo(expected);
        assertThat(allUsers).hasSize(0);
    }

    @Test
    void shouldDeleteUser_WhenUserNotExists_ThenThrowUserNotFoundException() {
        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() -> out.deleteUser(mockUser1.getId()));
    }

    @Test
    void shouldReturnAllUsers_ThenReturnTheseAllUsers() {
        User result1 = out.addUser(mockUser1);
        mockUser1.setId(result1.getId());
        User result2 = out.addUser(mockUser2);
        mockUser2.setId(result2.getId());
        Collection<User> allUsers = out.getAllUsers();

        assertThat(result1).isEqualTo(mockUser1);
        assertThat(result2).isEqualTo(mockUser2);
        assertThat(allUsers).hasSize(2);
    }
}