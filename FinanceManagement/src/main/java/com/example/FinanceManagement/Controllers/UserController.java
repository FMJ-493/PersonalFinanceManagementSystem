package com.example.FinanceManagementApp.Controllers;

import com.example.FinanceManagementApp.Models.Users;
import com.example.FinanceManagementApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/signUp")
    public ResponseEntity<Map<String,String>> signUp(@RequestBody Users user){
        Users userObj = userRepo.save(user);

        Map<String,String> response = new HashMap<>();

        if(userObj.getUser_id() != 0){
            response.put("Status","Signup success");

        }
        else{
            response.put("Status","Signup failed!");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signIn")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody Users user){
        List<Users> userObj = userRepo.loginValidation(user.getEmail(),user.getPassword());

        Map<String,String> response = new HashMap<>();

        if(userObj.size() > 0){
            response.put("Status","Sign in success");
            response.put("User Id",String.valueOf(userObj.get(0).getUser_id()));
            response.put("Name",String.valueOf(userObj.get(0).getName()));
        }
        else{
            response.put("Status","Sign in failed");
        }
        return ResponseEntity.ok(response);
    }
}
