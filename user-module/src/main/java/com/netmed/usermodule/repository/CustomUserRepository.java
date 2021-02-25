package com.netmed.usermodule.repository;

import com.netmed.usermodule.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * CustomUserRepository is a JPA Repository comprising Custom Queries for User based transactions
 *
 * @author Nataraj
 * @created 14/02/2021
 */
public interface CustomUserRepository {


    /**
     * findByUserName finds User by User name
     *
     * @param userName
     * @return User
     */
    User findByUserName(String userName);

    /**
     * findByUserName finds User by User name
     *
     * @param userName
     * @param pageable
     * @return Page of User List
     */
    Page<User> findByUserName(String userName, Pageable pageable);

    /**
     * findByUserName finds User by User name
     *
     * @param userName
     * @return List<User>
     */
    /* Additional Queries - Learning Query */
    @Query(value = "select * from netmed_user where user_name like ?1%", nativeQuery = true)
    List<User> findUserByUserName(String userName);

    /**
     * existsByUserName used to know occurrence of User record in Db
     *
     * @param userName
     * @return boolean
     */
    boolean existsByUserName(String userName);

    /**
     * findUserIdByUserName fetches user id using user name
     *
     * @param userName
     * @return Long
     */
    /* Additional Queries - Learning Query */
    @Query(value = "select user_id from netmed_user where user_name like ?1", nativeQuery = true)
    long findUserIdByUserName(String userName);
}
