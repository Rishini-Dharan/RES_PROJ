package com.techforge.reservation.exception;

public class TableAlreadyReservedException extends RuntimeException {

    public TableAlreadyReservedException(String msg){
        super(msg);
    }

}
