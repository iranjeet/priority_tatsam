package com.example.priority.dto.responce;

import com.example.priority.constant.BusinessConstant;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class GenericResponse {
    @NotNull
    private String message;
    @NotNull
    private String status = BusinessConstant.Status.SUCCESS;
    @NotNull
    private String details;

    public GenericResponse(@NotNull String message, @NotNull String status, @NotNull String details) {
        this.message = message;
        this.status = status;
        this.details = details;
    }
}
