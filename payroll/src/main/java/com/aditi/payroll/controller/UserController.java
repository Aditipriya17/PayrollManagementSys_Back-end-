package com.aditi.payroll.controller;

import com.aditi.payroll.collections.User;
import com.aditi.payroll.service.Interface.UserService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) throws NoSuchAlgorithmException {
        user.setId(System.currentTimeMillis());
        user.setPassword(sha256(user.getPassword()));
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    @PostMapping("/validate")
    public ResponseEntity<String> validateUser(@RequestBody User user) throws NoSuchAlgorithmException {
        User data = userService.getUserByEmail(user.getEmail());
        if (sha256(user.getPassword()).equals(data.getPassword())) {
            return new ResponseEntity<>("matched", HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("not matched", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.CONFLICT);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.FOUND);
    }

    public String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(input.getBytes());
        return Hex.encodeHexString(hash);
    }
}

