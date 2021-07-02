package com.example.priority.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ReqRatingForPriority {
    private Long userId;
    private List<Rating> rating;

    @Data
    public static class Rating {
        private Long priorityId;
        private Double rating;
        private Long Orders;
    }
}
