package com.example.priority.service;

import com.example.priority.dto.request.ReqPriorityDetails;
import com.example.priority.dto.request.ReqRatingForPriority;
import com.example.priority.dto.responce.GenericResponse;
import com.example.priority.dto.responce.ResAllPriority;

public interface PriorityService  {
    public GenericResponse addPriority(ReqPriorityDetails reqPriorityDetails) ;

    ResAllPriority getAllPriority();

    GenericResponse addRating(ReqRatingForPriority reqRatingForPriority) throws Exception;
}
