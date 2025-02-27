package com.example.FinanceManagementApp.Repository;

import com.example.FinanceManagementApp.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<Users,Long> {

    @Query(value="SELECT `user_id`, `email`, `name`, `password` FROM `users` WHERE `email`=?1 AND `password`=?2", nativeQuery = true)
    public List<Users> loginValidation(String email, String password);
}
