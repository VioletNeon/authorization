package violet.neon.authorization.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import violet.neon.authorization.exception.UserNotFoundException;
import violet.neon.authorization.model.User;
import violet.neon.authorization.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private final User mockUser1 = new User();
    private final User mockUser2 = new User();

    {
        mockUser1.setFullName("Ivan Ivanovich Ivanov");
        mockUser1.setDepartment(1);

        mockUser2.setFullName("Petr Petrovich Petrov");
        mockUser2.setDepartment(2);
    }

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldAddUser_ThenReturnThatUser() {
        mockUser1.setId("1");
        when(userRepository.save(any(User.class))).thenReturn(mockUser1);

        String result = userService.addUser(mockUser1);

        assertThat(result).isEqualTo(mockUser1.getId());

        verify(userRepository, times(1)).save(eq(mockUser1));
    }

    @Test
    void shouldFindUserById_ThenReturnThatUser() {
        mockUser1.setId("2");
        when(userRepository.findById(mockUser1.getId())).thenReturn(Optional.of(mockUser1));

        User result = userService.findUser(mockUser1.getId());

        assertThat(result).isEqualTo(mockUser1);

        verify(userRepository, times(1)).findById(eq(mockUser1.getId()));
    }

    @Test
    void shouldFindUserById_WhenUserNotExists_ThenThrowUserNotFoundException() {
        mockUser1.setId("3");
        when(userRepository.findById(mockUser1.getId())).thenReturn(Optional.empty());

        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() -> userService.findUser(mockUser1.getId()));

        verify(userRepository, times(1)).findById(eq(mockUser1.getId()));
    }

    @Test
    void shouldUpdateUser_WhenUserExists_ThenReturnThatUser() {
        mockUser1.setId("4");
        when(userRepository.save(any(User.class))).thenReturn(mockUser1);
        when(userRepository.findById(mockUser1.getId())).thenReturn(Optional.of(mockUser1));

        User result = userService.updateUser(mockUser1);

        assertThat(result).isEqualTo(mockUser1);

        verify(userRepository, times(1)).findById(eq(mockUser1.getId()));
        verify(userRepository, times(1)).save(eq(mockUser1));
    }

    @Test
    void shouldUpdateUser_WhenUserNotExists_ThenThrowUserNotFoundException() {
        mockUser1.setId("5");
        when(userRepository.findById(mockUser1.getId())).thenReturn(Optional.empty());

        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() -> userService.updateUser(mockUser1));

        verify(userRepository, times(1)).findById(eq(mockUser1.getId()));
    }

    @Test
    void shouldDeleteUser_ThenReturnThatUser() {
        mockUser1.setId("6");
        when(userRepository.findById(mockUser1.getId())).thenReturn(Optional.of(mockUser1));

        userService.deleteUser(mockUser1.getId());

        verify(userRepository, times(1)).findById(eq(mockUser1.getId()));
        verify(userRepository, times(1)).deleteById(eq(mockUser1.getId()));
    }

    @Test
    void shouldDeleteUser_WhenUserNotExists_ThenThrowUserNotFoundException() {
        mockUser1.setId("7");

        when(userRepository.findById(mockUser1.getId())).thenReturn(Optional.empty());

        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() -> userService.deleteUser(mockUser1.getId()));

        verify(userRepository, times(1)).findById(eq(mockUser1.getId()));
    }

    @Test
    void shouldReturnAllUsers_ThenReturnTheseAllUsers() {
        mockUser1.setId("8");
        mockUser2.setId("9");
        List<User> mockStudentList = List.of(mockUser1, mockUser2);

        when(userRepository.findAll()).thenReturn(mockStudentList);

        Collection<User> result = userService.getAllUsers();

        assertThat(result).isEqualTo(mockStudentList);
    }
}