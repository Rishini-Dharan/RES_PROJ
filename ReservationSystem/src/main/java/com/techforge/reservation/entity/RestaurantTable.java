package com.techforge.reservation.entity;

import com.techforge.reservation.enums.SeatingArea;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private Restaurant restaurant;

    private int tableNumber;

    private int capacity;

    @Enumerated(value = EnumType.STRING)
    private SeatingArea seatingArea;

    private BigDecimal premiumCharge;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public SeatingArea getSeatingArea() {
        return seatingArea;
    }

    public void setSeatingArea(SeatingArea seatingArea) {
        this.seatingArea = seatingArea;
    }

    public BigDecimal getPremiumCharge() {
        return premiumCharge;
    }

    public void setPremiumCharge(BigDecimal premiumCharge) {
        this.premiumCharge = premiumCharge;
    }
}
