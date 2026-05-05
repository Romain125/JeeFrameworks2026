package org.example;

public class GameNotFound extends RuntimeException{
    public GameNotFound(String message){
        super(message);
    }
}
