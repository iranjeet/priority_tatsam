package com.example.priority.service.impl;

import com.example.priority.constant.BusinessConstant;
import com.example.priority.domain.AssnPriorityUser;
import com.example.priority.domain.Priority;
import com.example.priority.domain.User;
import com.example.priority.dto.request.ReqPriorityDetails;
import com.example.priority.dto.request.ReqRatingForPriority;
import com.example.priority.dto.responce.GenericResponse;
import com.example.priority.dto.responce.ResAllPriority;
import com.example.priority.repository.AssnPriorityUserRepository;
import com.example.priority.repository.PriorityRepository;
import com.example.priority.repository.UserRepository;
import com.example.priority.service.PriorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    PriorityRepository priorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AssnPriorityUserRepository assnPriorityUserRepository;

    @Override
    public GenericResponse addPriority(ReqPriorityDetails reqPriorityDetails) {
        Priority priority = new Priority();
        Priority priorities = priorityRepository.findOneByName(reqPriorityDetails.getName());
        if (priorities != null) {
            log.info("Priority already exist with name " + reqPriorityDetails.getName());
            return new GenericResponse(BusinessConstant.Priority.ALREADY_EXIST, BusinessConstant.Status.FAILED, reqPriorityDetails.getName());
        }
        priority.setName(reqPriorityDetails.getName());
        priority.setCreatedDate(System.currentTimeMillis());
        priority.setUpdatedDate(System.currentTimeMillis());
        priorityRepository.save(priority);
        return new GenericResponse("Priority Added Successfully", BusinessConstant.Status.SUCCESS, priority.toString());
    }

    @Override
    public ResAllPriority getAllPriority() {
        List<Priority> priorities = priorityRepository.findAll();

        if (priorities.isEmpty()) {
            log.info("No priorities found");
            return (ResAllPriority) new GenericResponse(BusinessConstant.Priority.NO_PRIORITY_FOUND, BusinessConstant.Status.FAILED, BusinessConstant.Priority.NO_PRIORITY_FOUND);
        }
        List<ResAllPriority.Priority> priorityList = priorities.stream().map(x -> {
            ResAllPriority.Priority priority = new ResAllPriority.Priority();
            priority.setPriorityId(x.getId());
            priority.setName(x.getName());
            return priority;
        }).collect(Collectors.toList());
        ResAllPriority response = new ResAllPriority();
        response.setPriorityList(priorityList);
        response.setDetails(BusinessConstant.Priority.SUCCESS);
        response.setStatus(BusinessConstant.Status.SUCCESS);
        response.setMessage("All priorities returned Successfully");
        return response;
    }

    @Override
    @Transactional
    public GenericResponse addRating(ReqRatingForPriority reqRatingForPriority)  {
        List<Long> distinctOrder = new ArrayList<>();
        List<Long> distinctPriority = new ArrayList<>();
//        To find out whether any duplicate order or  priority is there or not
        for (ReqRatingForPriority.Rating distinct : reqRatingForPriority.getRating()) {
            if (distinctOrder.contains(distinct.getOrders()) || distinctPriority.contains(distinct.getPriorityId()))
                return new GenericResponse("Duplicate Order or Priority Found", BusinessConstant.Status.FAILED, distinct.getOrders() + "  " + distinct.getPriorityId());
            distinctOrder.add(distinct.getOrders());
            distinctPriority.add(distinct.getPriorityId());
        }
        if (reqRatingForPriority.getRating().isEmpty())
            return new GenericResponse("Noting To Update", BusinessConstant.Status.SUCCESS, "No Data found to update priority rating");
        try {
            User user = userRepository.findOneById(reqRatingForPriority.getUserId());
            if (user.getId() == null)
                return new GenericResponse("User not found with Id " + reqRatingForPriority.getUserId(), BusinessConstant.Status.FAILED, "");
            for (ReqRatingForPriority.Rating rts : reqRatingForPriority.getRating()) {
                GenericResponse response = addRatingToPriority(rts.getPriorityId(), user, rts.getRating(), rts.getOrders());
                if (BusinessConstant.Status.FAILED .equals(response.getStatus()))
                    return response;

            }
            return new GenericResponse("Rating Added Successfully", BusinessConstant.Status.SUCCESS, reqRatingForPriority.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericResponse("", BusinessConstant.Status.FAILED, "user not Found with " + reqRatingForPriority.getUserId());
        }

    }


    public GenericResponse addRatingToPriority(Long priorityId, User user, Double rating, Long order) {


        if (null == priorityId || null == rating || null == order)
            return new GenericResponse("No Fields can be null please fill all details", BusinessConstant.Status.FAILED, "Every fields are compulsory");
        if (rating > 5.0D || rating < 1.0D || rating.isNaN())
            return new GenericResponse("Ratting Should be Between 1 and 5", BusinessConstant.Status.FAILED, "" + rating);
        try {
            AssnPriorityUser assnPriorityUser = new AssnPriorityUser();
            Priority priority = priorityRepository.findOneById(priorityId);
            List<AssnPriorityUser> existingUser = assnPriorityUserRepository.findByUserAndPriority(user, priority);
            if (!existingUser.isEmpty()) {
                assnPriorityUser.setId(existingUser.get(0).getId());
            }
            if (priority.getId() == null)
                return new GenericResponse("Priority not found with Id " + priorityId, BusinessConstant.Status.FAILED, "");
            assnPriorityUser.setPriority(priority);
            assnPriorityUser.setUser(user);
            assnPriorityUser.setRating(rating);
            assnPriorityUser.setOrders(order);
            assnPriorityUser.setIsActive(true);
            log.info(assnPriorityUser.toString());
            assnPriorityUserRepository.save(assnPriorityUser);
            log.info("Rating Added Successfully");
            return new GenericResponse("Rating added Successfully", BusinessConstant.Status.SUCCESS, assnPriorityUser.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return new GenericResponse("Data Not Found", BusinessConstant.Status.FAILED, "No priority found for Priority Id " + priorityId);
        }
    }

}
