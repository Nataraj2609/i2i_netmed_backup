package com.netmed.usermodule.serviceImpl;

import com.netmed.usermodule.config.RabbitMqConfig;
import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.exception.DuplicateUserRecordFoundException;
import com.netmed.usermodule.exception.UserNotFoundException;
import com.netmed.usermodule.model.Role;
import com.netmed.usermodule.model.User;
import com.netmed.usermodule.repository.RoleRepository;
import com.netmed.usermodule.repository.UserRepository;
import com.netmed.usermodule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    private final RabbitTemplate rabbitTemplate;

    private final MessageChannel output;

    @Override
    @CachePut(value = "user")
    public UserDto createUser(UserDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        Role roleEntity = roleRepository.findByRoleName(userDto.getRoleName());
        userEntity.setRole(roleEntity);
        if (userRepository.existsByUserName(userEntity.getUserName()))
            throw new DuplicateUserRecordFoundException();
        userEntity = userRepository.save(userEntity);
        UserDto createdUserDto = modelMapper.map(userEntity, UserDto.class);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, createdUserDto);
        output.send(MessageBuilder.withPayload("Kafka Payload "+createdUserDto).build());
        return createdUserDto;
    }

    @Override
    @Cacheable(value = "user")
    public UserDto getUser(long userId) {
        Optional<User> userEntity = userRepository.findById(userId);
        if (!userEntity.isPresent())
            throw new UserNotFoundException();
        UserDto user = modelMapper.map(userEntity.get(), UserDto.class);
        return user;
    }

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

    @Override
    @CacheEvict(value = "user")
    public void deleteUser(long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Cacheable(value = "user")
    public List<UserDto> getAllUsers(int page, int limit, String orderBy) {
        Sort.Direction sortDirection = orderBy.equals("des") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "userName"));
        List<User> userList = userRepository.findAll(pageable).toList();
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "user")
    public List<UserDto> searchUser(String search, int page, int limit, String orderBy) {
        Sort.Direction sortDirection = orderBy.equals("des") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "userName"));
        List<User> userList = userRepository.findByUserName(search, pageable).toList();
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "user")
    public List<UserDto> search(String search) {
        List<User> userList = userRepository.findUserByUserName(search);
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public Long getUserIdByUserName(String userName) {
        long userId = userRepository.findUserIdByUserName(userName);
        return userId;
    }
}
