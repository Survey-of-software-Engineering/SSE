package com.dsms.app.repository;

import com.dsms.app.entity.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends MongoRepository<CreditCard,String> {

}
