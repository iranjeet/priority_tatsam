package com.example.priority.controller;

import com.example.priority.dto.request.ExpenseRequest;
import com.example.priority.dto.request.RequestUserDetails;
import com.example.priority.dto.responce.GenericResponse;
import com.example.priority.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tatsam/user/")
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;
//For Registering new user
    @PostMapping("register")
    GenericResponse registerUser(@RequestBody RequestUserDetails userDetails) {
        log.info(userDetails.toString());
        GenericResponse response = userService.registerUser(userDetails);
        log.info(response.toString());
        return response;
    }


    @PostMapping("createExpense")
    GenericResponse createExpense(@RequestBody ExpenseRequest expenseRequest){
        return userService.createExpense(expenseRequest);
    }
}
