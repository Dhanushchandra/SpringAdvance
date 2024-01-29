package com.usersDetails.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorStructure {

    private HttpStatus status;
    private int status_code;
    private String message;
    private String description;

}
