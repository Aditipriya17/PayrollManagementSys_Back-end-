package com.aditi.payroll.service.Interface;

import com.aditi.payroll.collections.User;

import java.util.List;

public interface UserService {
    User save(User user);

    List<User> getAllUser();

    User getUserByEmail(String email);
}
