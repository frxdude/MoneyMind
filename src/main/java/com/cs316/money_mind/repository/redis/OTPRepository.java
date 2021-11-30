package com.cs316.money_mind.repository.redis;

import com.cs316.money_mind.entity.redis.OneTimePassword;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * OTPRepository
 *
 * @author Ishdorj Sainjargal
 **/

public interface OTPRepository extends CrudRepository<OneTimePassword, String> {

    Optional<OneTimePassword> findByTypeAndId(String type, String id);

}
