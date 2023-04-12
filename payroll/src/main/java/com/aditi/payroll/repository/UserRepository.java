package com.aditi.payroll.repository;

import com.aditi.payroll.collections.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
}
