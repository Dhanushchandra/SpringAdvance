package com.usersDetails.users.exception;

public class UserExceptions {

    public static class UserNotFound extends RuntimeException{
       public UserNotFound(String message){
            super(message);
        }

    }

}
