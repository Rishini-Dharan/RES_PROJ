package com.techforge.reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String address;

    private LocalTime openingTime;

    private LocalTime closingTime;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<RestaurantTable> tables;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<ExperiencePackage> packages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public List<RestaurantTable> getTables() {
        return tables;
    }

    public void setTables(List<RestaurantTable> tables) {
        this.tables = tables;
    }

    public void addTable(RestaurantTable table){
        tables.add(table);
    }

    public void addPackage(ExperiencePackage experiencePackage){
        packages.add(experiencePackage);
    }
}
