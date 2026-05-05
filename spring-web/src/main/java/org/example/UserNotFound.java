package org.example;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message){
        super(message);
    }
}
