package com.dsms.app.repository;

import com.dsms.app.entity.CouponCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponCodeRepository extends MongoRepository<CouponCode, String> {

    CouponCode getCouponCodeByCode(String code);

    @Query("{}")
    List<CouponCode> getAllCouponCodes();
}
