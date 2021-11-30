package com.cs316.money_mind.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.List;

/**
 * User
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "USERS", indexes = {
        @Index(name = "user_id_idx", columnList = "id", unique = true),
        @Index(name = "user_email_idx", columnList = "email", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Audit {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userSeq")
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

}
