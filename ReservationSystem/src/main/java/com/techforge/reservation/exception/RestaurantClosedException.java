package com.techforge.reservation.exception;

public class RestaurantClosedException extends RuntimeException {

    public RestaurantClosedException(String message){
        super(message);
    }

}
