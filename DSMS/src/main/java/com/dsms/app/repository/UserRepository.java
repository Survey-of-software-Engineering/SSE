package com.dsms.app.repository;

import com.dsms.app.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    User getUserByUserMailId(String mailId);
}
