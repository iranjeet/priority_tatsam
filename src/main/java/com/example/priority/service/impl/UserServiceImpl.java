package com.example.priority.service.impl;

import com.example.priority.constant.BusinessConstant;
import com.example.priority.domain.User;
import com.example.priority.dto.request.RequestUserDetails;
import com.example.priority.dto.responce.GenericResponse;
import com.example.priority.repository.UserRepository;
import com.example.priority.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
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
        user.setCreatedDate(System.currentTimeMillis());
        user.setUpdatedDate(System.currentTimeMillis());
        userRepository.save(user);
        return new GenericResponse("User Added Successfully",BusinessConstant.Status.SUCCESS,String.valueOf(user));
    }

    public GenericResponse activateUser(Long id) {
        try {
            User user= userRepository.getById(id);
            if(null == user){
                return new GenericResponse(BusinessConstant.User.USER_NOT_EXIST,BusinessConstant.Status.FAILED,id.toString());
            }
            user.setUpdatedDate(System.currentTimeMillis());
            user.setIsActive(true);
            userRepository.save(user);

        }catch (Exception e){
            log.info(e.toString());
            return new GenericResponse(BusinessConstant.User.USER_NOT_EXIST,BusinessConstant.Status.FAILED,id.toString());
        }
        return new GenericResponse("User Activated Successfully",BusinessConstant.Status.SUCCESS,id.toString());
    }
}
