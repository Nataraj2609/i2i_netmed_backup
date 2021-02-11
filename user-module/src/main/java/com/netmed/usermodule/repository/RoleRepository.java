package com.netmed.usermodule.repository;

import com.netmed.usermodule.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository is a JPA Repository to talk with DB
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
