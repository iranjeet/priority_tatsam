package com.example.priority.service.impl;

import com.example.priority.constant.BusinessConstant;
import com.example.priority.domain.Expense;
import com.example.priority.domain.User;
import com.example.priority.dto.request.ExpenseRequest;
import com.example.priority.dto.request.RequestUserDetails;
import com.example.priority.dto.responce.GenericResponse;
import com.example.priority.repository.ExpenseRepository;
import com.example.priority.repository.UserRepository;
import com.example.priority.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExpenseRepository expenseRepository;
    @Override
    public GenericResponse registerUser(RequestUserDetails userDetails) {
        User user=new User();
        user.setName(userDetails.getName());
        User users=userRepository.findOneByEmail(userDetails.getEmail());
        if(null != users){
            log.info("User already present with email "+userDetails.getEmail());
            return new GenericResponse(BusinessConstant.User.USER_PRESENT_WITH_THIS_EMAIL,BusinessConstant.Status.FAILED,userDetails.getEmail());
        }
        user.setEmail(userDetails.getEmail());
        userRepository.save(user);
        return new GenericResponse("User Added Successfully",BusinessConstant.Status.SUCCESS,String.valueOf(user));
    }


    public GenericResponse createExpense(ExpenseRequest expenseRequest) {
        Expense expense=new Expense();
        expense.setName(expenseRequest.getExpenseName());
        expense.setTotalAmount(Double.valueOf(expenseRequest.getExpenseAmount()));
        expense.setWhoGaveMoney(userRepository.findByName(expenseRequest.getWhoPaid()));
        Set<User> userSet=new HashSet<>();
        expenseRequest.getUsers().forEach( user-> {
            userSet.add(userRepository.findByName(user));
        });
        expenseRepository.save(expense);

        return null;
    }
}
