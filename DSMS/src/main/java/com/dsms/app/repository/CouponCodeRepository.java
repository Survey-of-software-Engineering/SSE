package com.dsms.app.repository;

import com.dsms.app.entity.CouponCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponCodeRepository extends MongoRepository<CouponCode, String> {

}
