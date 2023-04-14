package com.aditi.payroll.repository;

import com.aditi.payroll.collections.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByEmail(String email);
}
