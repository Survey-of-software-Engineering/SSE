package com.dsms.app.repository;

import com.dsms.app.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {

    @Query("{}")
    List<Payment> getAllPayments();
}
