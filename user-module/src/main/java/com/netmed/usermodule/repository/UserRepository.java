package com.netmed.usermodule.repository;

import com.netmed.usermodule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository is a JPA Repository to Persist User Entity with DB
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

}
