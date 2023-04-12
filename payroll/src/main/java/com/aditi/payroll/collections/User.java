package com.aditi.payroll.collections;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
    private String name;
    private char gender;
    private String email;
    private String password;
}
