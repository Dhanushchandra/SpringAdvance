package com.usersDetails.users.advice;

import com.usersDetails.users.dto.ErrorStructure;
import com.usersDetails.users.exception.EmailExceptions;
import com.usersDetails.users.exception.UserExceptions;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(EmailExceptions.EmailAlreadyExistsExceptions.class)
    public ResponseEntity<ErrorStructure> handleEmailExist(EmailExceptions.EmailAlreadyExistsExceptions emailAlreadyExistsExceptions){
        ErrorStructure errorStructure = new ErrorStructure(HttpStatus.BAD_REQUEST, 400,"Email Exist!","The Email id passed in the body already exist in the DB");
        return new ResponseEntity<>(errorStructure, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExceptions.UserNotFound.class)
    public ResponseEntity<ErrorStructure> userNotFound(UserExceptions.UserNotFound userNotFound){
        ErrorStructure errorStructure = new ErrorStructure(HttpStatus.NOT_FOUND, 404, "User Doesn't Exist", "The given User ID doesn't exist in the DB");
        return new ResponseEntity<>(errorStructure,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorStructure> handleValidationExceptions(
            ConstraintViolationException ex) {

        List<String> validationErrors = ex.getConstraintViolations()
                .stream()
                .map(violations -> String.format("%s: %s", violations.getPropertyPath(), violations.getMessage()))
                .collect(Collectors.toList());

        String errorMessage = String.join(", ", validationErrors);

        ErrorStructure errorStructure = new ErrorStructure(HttpStatus.BAD_REQUEST, 400, "Validation failed for entity during persist time.", errorMessage);
        return new ResponseEntity<>(errorStructure,HttpStatus.BAD_REQUEST);
    }


}
