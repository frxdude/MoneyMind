package com.cs316.money_mind.repository;

import com.cs316.money_mind.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * UserRepository
 *
 * @author Sainjargal Ishdorj
 **/

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
