package com.example.employee.otp.repository;

import com.example.employee.otp.model.OtpState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OTPRepository extends MongoRepository<OtpState, String> {

}
