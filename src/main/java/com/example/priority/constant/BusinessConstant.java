package com.example.priority.constant;

public interface BusinessConstant {
    interface User {
        String USER_PRESENT_WITH_THIS_EMAIL = "User is already present with this email id";
        String NULL_DATA = "data cannot be NULL";
        String USER_NOT_EXIST="User not exist with the details provided";
    }

    interface Priority {

        String ALREADY_EXIST = "Priority already exist";
        String NO_PRIORITY_FOUND = "No Priority Found";
        String SUCCESS = "Success";
    }

    interface Status {
        String SUCCESS = "SUCCESSFUL";
        String FAILED = "FAILED";
        String PENDING = "PENDING";
    }

}
