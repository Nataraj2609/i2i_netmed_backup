package com.netmed.usermodule.repository;

import com.netmed.usermodule.dto.UserDto;
import com.netmed.usermodule.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository is a JPA Repository to talk with DB
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);
}
