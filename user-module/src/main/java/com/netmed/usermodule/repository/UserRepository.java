package com.netmed.usermodule.repository;

import com.netmed.usermodule.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository is a JPA Repository to Persist User Entity with DB
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    User findByUserName(String userName);

    Page<User> findByUserName(String userName, Pageable pageable);

    /* Additional Queries - Learning Query */
    @Query(value = "select * from netmed_user where user_name like ?1%", nativeQuery = true)
    List<User> findUserByUserName(String userName);

    boolean existsByUserName(String userName);
}
