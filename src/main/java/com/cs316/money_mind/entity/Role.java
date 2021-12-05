package com.cs316.money_mind.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role
 *
 * @author Sainjargal Ishdorj
 **/

public enum Role implements GrantedAuthority {

    ROLE_TEMP,
    //0-14 years
    ROLE_CHILDREN,
    //15-24 years
    ROLE_YOUTH,
    //25-64 years
    ROLE_ADULT,
    //65+ years
    ROLE_SENIOR;

    public String getAuthority() {
        return name();
    }

}
