package com.netmed.usermodule.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
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

import java.io.IOException;
import java.util.List;
import java.util.Map;
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

    private final RestHighLevelClient restHighLevelClient;

    private final String INDEX = "netmed_user";

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @CachePut(value = "user")
    public UserDto createUser(UserDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        Role roleEntity = roleRepository.findByRoleName(userDto.getRoleName());
        userEntity.setRole(roleEntity);
        if (userRepository.existsByUserName(userEntity.getUserName()))
            throw new DuplicateUserRecordFoundException();
        userEntity = userRepository.save(userEntity);

        Map<String, Object> dataMap = objectMapper.convertValue(userEntity, Map.class);
        IndexRequest indexRequest = new IndexRequest(INDEX).source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }


        UserDto createdUserDto = modelMapper.map(userEntity, UserDto.class);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, createdUserDto);
        output.send(MessageBuilder.withPayload(createdUserDto).build());
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


        Map<String, Object> params = objectMapper.convertValue(oldUserEntity, Map.class);
        UpdateByQueryRequest request = new UpdateByQueryRequest(INDEX);
        request.setConflicts("proceed");
        request.setQuery(new TermQueryBuilder("userId", oldUserEntity.getUserId()));
        request.setScript(new Script(ScriptType.INLINE, "painless", "ctx._source.putAll(params)", params));
        try {
            BulkByScrollResponse bulkResponse = restHighLevelClient.updateByQuery(request, RequestOptions.DEFAULT);
            System.out.println("Updated Response doc : "+bulkResponse.getUpdated());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return modelMapper.map(oldUserEntity, UserDto.class);
    }

    @Override
    @CacheEvict(value = "user")
    public void deleteUser(long userId) {
        try {
            userRepository.deleteById(userId);

            DeleteByQueryRequest request = new DeleteByQueryRequest(INDEX);
            request.setConflicts("proceed");
            request.setQuery(new TermQueryBuilder("userId", userId));
            BulkByScrollResponse bulkResponse = restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
            System.out.println("------>  Deleted Docs = " + bulkResponse.getDeleted());


        } catch (EmptyResultDataAccessException | IOException e) {
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
