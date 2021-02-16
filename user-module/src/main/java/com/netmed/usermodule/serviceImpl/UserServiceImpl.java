package com.netmed.usermodule.serviceImpl;

import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.exception.DuplicateUserRecordFoundException;
import com.netmed.usermodule.exception.UserNotFoundException;
import com.netmed.usermodule.model.Role;
import com.netmed.usermodule.model.User;
import com.netmed.usermodule.repository.RoleRepository;
import com.netmed.usermodule.repository.UserRepository;
import com.netmed.usermodule.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * createUser Saves the user details
     *
     * @param userDto
     * @return Response status with saved user record
     */
    @Override
    @CachePut(value = "user")
    public UserDto createUser(UserDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        Role roleEntity = roleRepository.findByRoleName(userDto.getRoleName());
        userEntity.setRole(roleEntity);
        if (userRepository.existsByUserName(userEntity.getUserName()))
            throw new DuplicateUserRecordFoundException();
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
    @Cacheable(value = "user")
    public UserDto getUser(long userId) {
        Optional<User> userEntity = userRepository.findById(userId);
        if (!userEntity.isPresent())
            throw new UserNotFoundException();
        UserDto user = modelMapper.map(userEntity.get(), UserDto.class);
        return user;
    }

    /**
     * Update Service for updating the user details for the id
     *
     * @param userDto
     * @return Requested User Detail
     */
    @Override
    @CachePut(value = "user")
    public UserDto updateUser(UserDto userDto) {
        if (!userRepository.existsByUserName(userDto.getUserName()))
            throw new UserNotFoundException();
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
    @CacheEvict(value = "user")
    public void deleteUser(long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }
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
    @Cacheable(value = "user")
    public List<UserDto> getAllUsers(int page, int limit, String orderBy) {
        Sort.Direction sortDirection = orderBy.equals("des") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "userName"));
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
    @Cacheable(value = "user")
    public List<UserDto> searchUser(String search, int page, int limit, String orderBy) {
        Sort.Direction sortDirection = orderBy.equals("des") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "userName"));
        List<User> userList = userRepository.findByUserName(search, pageable).toList();
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    /**
     * Search all the user details matching the given condition (Native Query)
     *
     * @param search
     * @return List of User Details
     */
    @Override
    @Cacheable(value = "user")
    public List<UserDto> search(String search) {
        List<User> userList = userRepository.findUserByUserName(search);
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public long getUserIdByUserName(String userName) {
        long userId = userRepository.findUserIdByUserName(userName);
        return userId;
    }
}
