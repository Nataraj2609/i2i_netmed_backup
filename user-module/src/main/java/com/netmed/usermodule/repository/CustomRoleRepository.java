package com.netmed.usermodule.repository;

import com.netmed.usermodule.model.Role;

/**
 * CustomRoleRepository is a JPA Repository comprising Custom Queries for Role based transactions
 *
 * @author Nataraj
 * @created 14/02/2021
 */
public interface CustomRoleRepository {

    /**
     * findByRoleName method is used to fetch role name of a user
     * @param roleName
     * @return
     */
    Role findByRoleName(String roleName);
}
