package com.netmed.usermodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Role is a JPA Entity
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Entity
@Table(name = "netmed_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long roleId;

    @Column(name = "role_name", length = 10)
    private String roleName;

}
