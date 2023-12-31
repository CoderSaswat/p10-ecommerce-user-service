package com.ecommerce.user.repository;

import com.ecommerce.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    boolean existsByEmail(String email);
}
