package com.example.priority.controller;

import com.example.priority.dto.request.ReqPriorityDetails;
import com.example.priority.dto.request.ReqRatingForPriority;
import com.example.priority.dto.responce.GenericResponse;
import com.example.priority.dto.responce.ResAllPriority;
import com.example.priority.service.PriorityService;
import com.example.priority.service.impl.PriorityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tatsam/user/")
@Slf4j
public class PriorityController {
    @Autowired
    PriorityServiceImpl priorityService;

//    For Adding new priority
    @PostMapping("addPriority")
    private GenericResponse addPriority(@RequestBody ReqPriorityDetails reqPriorityDetails) {
        log.info(reqPriorityDetails.toString());
        GenericResponse response = priorityService.addPriority(reqPriorityDetails);
        log.info(response.toString());
        return response;
    }
//For fetching all the priority
    @GetMapping("getAllPriority")
    private ResAllPriority getAllPriority() {
        log.info("In PriorityController -> GetAllPriority");
        ResAllPriority resAllPriority = priorityService.getAllPriority();
        log.info(resAllPriority.toString());
        return resAllPriority;
    }
//for adding priority along with order and rating
    @PostMapping("addRatingToPriorites")
    private GenericResponse addRating(@RequestBody ReqRatingForPriority reqRatingForPriority) throws Exception {
        log.info(reqRatingForPriority.toString());
        GenericResponse response = priorityService.addRating(reqRatingForPriority);
        log.info(response.toString());
        return response;
    }

}
