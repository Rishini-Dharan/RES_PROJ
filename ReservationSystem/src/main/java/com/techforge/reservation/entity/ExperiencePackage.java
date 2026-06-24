package com.techforge.reservation.entity;

import com.techforge.reservation.configuration.InclusionConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
public class ExperiencePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private Restaurant restaurant;

    private String name;

    private String description;

    private BigDecimal price;

    @Convert(converter = InclusionConverter.class)
    private List<String> inclusions;

}
