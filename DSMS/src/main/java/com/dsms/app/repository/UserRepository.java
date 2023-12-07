package com.dsms.app.repository;

import com.dsms.app.entity.Order;
import com.dsms.app.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    User getUserByUserMailId(String mailId);

    User getUserByUserId(String userId);

    List<User> getUsersByRolesContainingAndOrdersNotNull(List<String> roles);
}
