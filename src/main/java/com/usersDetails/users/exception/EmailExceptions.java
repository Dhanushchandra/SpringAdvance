package com.usersDetails.users.exception;

public class EmailExceptions {

    public static class EmailAlreadyExistsExceptions extends Exception{
        public EmailAlreadyExistsExceptions(String message){
            super(message);
        }
    }

}
