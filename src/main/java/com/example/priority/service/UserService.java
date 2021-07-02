package com.example.priority.service;

import com.example.priority.dto.request.RequestUserDetails;
import com.example.priority.dto.responce.GenericResponse;

public interface UserService {
    public GenericResponse registerUser(RequestUserDetails userDetails);
}
