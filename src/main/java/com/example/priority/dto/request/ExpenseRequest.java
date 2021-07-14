package com.example.priority.dto.request;

import lombok.Data;

import java.util.List;
@Data
public class ExpenseRequest {

    private String expenseName, expenseAmount, whoPaid;
    List<String> users;
    List<Double> percentageShare;

}
