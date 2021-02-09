package com.netmed.usermodule.serviceImpl;

import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.model.Role;
import com.netmed.usermodule.model.User;
import com.netmed.usermodule.repository.RoleRepository;
import com.netmed.usermodule.repository.UserRepository;
import com.netmed.usermodule.service.UserService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserServiceImpl is a Implementation of UserService
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Service
@Data
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    /**
     * createUser Saves the user details
     *
     * @param userDto
     * @return Response status with saved user record
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        Role roleEntity = roleRepository.findByRoleName(userDto.getRoleName());
        userEntity.setRole(roleEntity);

        userEntity = userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }

    /**
     * Get the user details based on id
     *
     * @param userId
     * @return Requested User Detail
     */
    @Override
    public UserDto getUser(long userId) {
        Optional<User> userEntity = userRepository.findById(userId);
        System.out.println(userEntity);
        UserDto user = modelMapper.map(userEntity.get(), UserDto.class);
        System.out.println(user);
        return user;
    }

    /**
     * Update Service for updating the user details for the id
     *
     * @param userDto
     * @return Requested User Detail
     */
    @Override
    public UserDto updateUser(UserDto userDto) {
        User oldUserEntity = userRepository.findByUserName(userDto.getUserName());
        Role roleEntity = roleRepository.findByRoleName(userDto.getRoleName());
        oldUserEntity.setRole(roleEntity);
        oldUserEntity.setPassword(userDto.getPassword());

        oldUserEntity = userRepository.save(oldUserEntity);
        return modelMapper.map(oldUserEntity, UserDto.class);
    }

    /**
     * Delete the user details for the id
     *
     * @param userId
     * @return No Content
     */
    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Retrieves all the user details matching the given condition
     *
     * @param orderBy
     * @param page
     * @param limit
     * @return user list
     */
    @Override
    public List<UserDto> getAllUsers(int page, int limit, String orderBy) {
        Pageable pageable;
        if (orderBy.equals("des"))
            pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "userName"));
        else
            pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "userName"));
        List<User> userList = userRepository.findAll(pageable).toList();
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    /**
     * Search all the user details matching the given condition
     *
     * @param search
     * @param limit
     * @param page
     * @param orderBy
     * @return List of User Details
     */
    @Override
    public List<UserDto> searchUser(String search, int page, int limit, String orderBy) {
        Pageable pageable;
        if (orderBy.equals("des"))
            pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "userName"));
        else
            pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "userName"));
        List<User> userList = userRepository.findByUserName(search, pageable).toList();
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> search(String search) {
        List<User> userList = userRepository.findUserByUserName(search);
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
}
