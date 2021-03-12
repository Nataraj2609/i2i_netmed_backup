package com.netmed.usermodule.repository;

import com.netmed.usermodule.model.User;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository is a JPA Repository to Persist User Entity with DB
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Repository
@JaversSpringDataAuditable
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

}
