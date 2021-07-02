package com.example.priority.dto.responce;

import lombok.Data;

import java.util.List;

@Data
public class ResAllPriority extends GenericResponse{
    List<Priority> priorityList;
    @Data
    public static class Priority{
        private Long priorityId;
        private String Name;
    }
}
