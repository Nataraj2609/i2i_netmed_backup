package com.netmed.usermodule.serviceImpl;

import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.exception.DuplicateUserRecordFoundException;
import com.netmed.usermodule.exception.UserNotFoundException;
import com.netmed.usermodule.model.Role;
import com.netmed.usermodule.model.User;
import com.netmed.usermodule.repository.UserRepository;
import com.netmed.usermodule.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * UserServiceImplTest class is used to do unit testing for Business logic of User Micro Service
 *
 * @author Nataraj M
 * @CreatedOn 23 March 2021
 */
@SpringBootTest
class UserServiceImplTest {

    /* The UserService*/
    @Autowired
    private UserService userService;

    /* The UserRepository*/
    @MockBean(answer= Answers.RETURNS_DEFAULTS)
    private UserRepository userRepository;

    /* The ModelMapper*/
    @Autowired
    private ModelMapper modelMapper;

    /**
     * createUserTest method is used to do unit testing for createUser() Business logic in service class
     */
    @Test
    public void createUserTest() throws IOException {
        Role role = new Role(2, "Patient");
        User user = new User(0, "SivaKarthik", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(userRepository.save(user)).thenReturn(user);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        assertEquals(userDto, userService.createUser(userDto));
    }

    /**
     * getUserTest method is used to do unit testing for getUser() Business logic in service class
     */
    @Test
    public void getUserTest() {
        long userId = 9;
        Role role = new Role(2, "Patient");
        User user = new User(userId, "John Wick", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        assertEquals(userDto, userService.getUser(userId));
    }

    /**
     * updateUserTest method is used to do unit testing for updateUser() Business logic in service class
     */
    @Test
    public void updateUserTest() throws IOException {
        Role role = new Role(2, "Patient");
        User oldUserRecord = new User(0, "SivaKarthik", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(userRepository.findByUserName(oldUserRecord.getUserName())).thenReturn(oldUserRecord);
        when(userRepository.existsByUserName(oldUserRecord.getUserName())).thenReturn(Boolean.TRUE);
        User updateUserRecord = new User(0, "SivaKarthik", "123456", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(userRepository.save(updateUserRecord)).thenReturn(updateUserRecord);
        UserDto userDto = modelMapper.map(updateUserRecord, UserDto.class);
        assertEquals(userDto, userService.updateUser(userDto));
    }

    /**
     * deleteUserTest method is used to do unit testing for deleteUser() Business logic in service class
     */
    @Test
    public void deleteUserTest() throws IOException {
        long userId = 999;
        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    /**
     * getAllUsersTest method is used to do unit testing for getAllUsers() Business logic in service class
     */
    @Test
    public void getAllUsersTest() {
        Role role = new Role(2, "Patient");
        User user1 = new User(9, "John Wick", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        User user2 = new User(10, "K Hrithik", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        User user3 = new User(10, "Kamal Hassan", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        List<User> userList = new ArrayList<User>(Stream.of(user1, user2, user3).collect(Collectors.toList()));
        Page<User> userPage = new PageImpl<>(userList);

        Sort.Direction sortDirection = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(0, 3, Sort.by(sortDirection, "userName"));
        when(userRepository.findAll(pageable)).thenReturn(userPage);

        List<UserDto> userDtoList = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        assertEquals(userDtoList, userService.getAllUsers(0, 3, "des"));
    }

    /**
     * searchUserTest method is used to do unit testing for searchUser() Business logic in service class
     */
    @Test
    public void searchUserTest() {
        String search = "John";
        Role role = new Role(2, "Patient");
        User user1 = new User(9, "John Wick", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        List<User> userList = new ArrayList<User>(Stream.of(user1).collect(Collectors.toList()));
        Page<User> userPage = new PageImpl<>(userList);

        Sort.Direction sortDirection = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(0, 3, Sort.by(sortDirection, "userName"));
        when(userRepository.findByUserName(search, pageable)).thenReturn(userPage);
        List<UserDto> userDtoList = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        assertEquals(userDtoList, userService.searchUser(search, 0, 3, "des"));
    }

    /**
     * searchTest method is used to do unit testing for search() Business logic in service class
     */
    @Test
    public void searchTest() {
        String search = "John";
        Role role = new Role(2, "Patient");
        User user1 = new User(9, "John Wick", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        List<User> userList = new ArrayList<User>(Stream.of(user1).collect(Collectors.toList()));
        when(userRepository.findUserByUserName(search)).thenReturn(userList);
        List<UserDto> userDtoList = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        assertEquals(userDtoList, userService.search(search));
    }

    /**
     * getUserIdByUserNameTest method is used to do unit testing for getUserIdByUserName() Business logic in service class
     */
    @Test
    public void getUserIdByUserNameTest() {
        String userName = "John Wick";
        when(userRepository.findUserIdByUserName(userName)).thenReturn(Long.valueOf(2));
        assertEquals(2, userService.getUserIdByUserName(userName));
    }

    /**
     * DuplicateUserRecordFoundExceptionTest method is used to do unit testing for DuplicateUserRecordFoundException Business logic in Exceptions
     */
    @Test
    public void DuplicateUserRecordFoundExceptionTest() throws IOException {
        Role role = new Role(2, "Patient");
        User user = new User(0, "SivaKarthik", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(userRepository.existsByUserName(user.getUserName())).thenReturn(true);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        DuplicateUserRecordFoundException thrownException = assertThrows(DuplicateUserRecordFoundException.class, () -> {
            userService.createUser(userDto);
        });
        assertEquals("Duplicate User Record - A Record with same user name already Exists", thrownException.getMessage());
    }

    /**
     * UserNotFoundExceptionTest method is used to do unit testing for UserNotFoundException Business logic in Exceptions
     */
    @Test
    public void UserNotFoundExceptionTest() throws IOException {
        Role role = new Role(2, "Patient");
        User user = new User(0, "SivaKarthik", "abcdefghi", role, "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"), "Doctor Hari", LocalDateTime.parse("2021-03-19T16:17:30.016413500"));
        when(userRepository.existsByUserName(user.getUserName())).thenReturn(false);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        UserNotFoundException thrownException = assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(userDto);
        });
        assertEquals("User Record not found in the Database", thrownException.getMessage());
    }

    /**
     * getUser_UserNotFoundExceptionTest method is used to do unit testing for getUser_UserNotFoundExceptionTest Business logic in Exceptions
     */
    @Test
    public void getUser_UserNotFoundExceptionTest() throws IOException {
        when(userRepository.findById(Long.valueOf(2))).thenReturn(Optional.empty());
        UserNotFoundException thrownException = assertThrows(UserNotFoundException.class, () -> {
            userService.getUser(Long.valueOf(2));
        });
        assertEquals("User Record not found in the Database", thrownException.getMessage());
    }
}