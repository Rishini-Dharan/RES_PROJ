package com.techforge.reservation.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(RestaurantClosedException.class)
    public String restaurantClosed(RestaurantClosedException exception){
        return exception.getMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TableAlreadyReservedException.class)
    public String tableBooked(TableAlreadyReservedException exception){
        return exception.getMessage();
    }

}
