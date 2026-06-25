package com.techforge.reservation.controller;

import com.techforge.reservation.entity.Reservation;
import com.techforge.reservation.entity.RestaurantTable;
import com.techforge.reservation.repository.CustomerRepository;
import com.techforge.reservation.repository.ExperiencePackageRepository;
import com.techforge.reservation.repository.RestaurantTableRepository;
import com.techforge.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.Set;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    private final CustomerRepository customerRepository;

    private final RestaurantTableRepository tableRepository;

    private final ExperiencePackageRepository packageRepository;

    public ReservationController(ReservationService reservationService, CustomerRepository customerRepository, RestaurantTableRepository tableRepository, ExperiencePackageRepository packageRepository) {
        this.reservationService = reservationService;
        this.customerRepository = customerRepository;
        this.tableRepository = tableRepository;
        this.packageRepository = packageRepository;
    }

    @PostMapping("/reservations")
    @ResponseBody
    public String reserveTable(@ModelAttribute Reservation reservation, @RequestParam(required = false) Set<Long> packageIds, @RequestParam long customerId, @RequestParam long tableId){
        reservationService.createReservation(reservation, packageIds, customerId, tableId);
        return "Reservation completed successfully";
    }

    @GetMapping("/reserve/new")
    public String reservationForm(Model model){
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("tables", tableRepository.findAll());
        model.addAttribute("packages", packageRepository.findAll());

        return "reservation-form";
    }

}
