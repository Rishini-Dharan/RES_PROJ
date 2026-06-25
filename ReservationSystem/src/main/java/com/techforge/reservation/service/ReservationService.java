package com.techforge.reservation.service;

import com.techforge.reservation.entity.ExperiencePackage;
import com.techforge.reservation.entity.Reservation;
import com.techforge.reservation.entity.Restaurant;
import com.techforge.reservation.entity.RestaurantTable;
import com.techforge.reservation.enums.BookingStatus;
import com.techforge.reservation.exception.RestaurantClosedException;
import com.techforge.reservation.exception.TableAlreadyReservedException;
import com.techforge.reservation.repository.CustomerRepository;
import com.techforge.reservation.repository.ExperiencePackageRepository;
import com.techforge.reservation.repository.ReservationRepository;
import com.techforge.reservation.repository.RestaurantRepository;
import com.techforge.reservation.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final RestaurantRepository restaurantRepository;

    private final CustomerRepository customerRepository;

    private final RestaurantTableRepository tableRepository;

    private final ExperiencePackageRepository packageRepository;

    public ReservationService(ReservationRepository reservationRepository, RestaurantRepository restaurantRepository, CustomerRepository customerRepository, RestaurantTableRepository tableRepository, ExperiencePackageRepository packageRepository) {
        this.reservationRepository = reservationRepository;
        this.restaurantRepository = restaurantRepository;
        this.customerRepository = customerRepository;
        this.tableRepository = tableRepository;
        this.packageRepository = packageRepository;
    }

    @Transactional
    public Reservation createReservation(Reservation reservation, Set<Long> packageIds, long customerId, long tableId){
        Restaurant restaurant = restaurantRepository.findById(1L).get();
        if(!isWithinBusinessHour(reservation.getStartTime(), reservation.getEndTime(), restaurant)){
            throw new RestaurantClosedException("Restaurant is closed");
        }
        RestaurantTable table = tableRepository.findById(tableId).orElseThrow();
        if(reservationRepository.checkForBooking(table.getId(), reservation.getStartTime(), reservation.getEndTime())){
            throw new TableAlreadyReservedException("The table is already booked for this time");
        }
        reservation.setCustomer(customerRepository.getReferenceById(customerId));
        reservation.setTable(table);
        reservation.setStatus(BookingStatus.BOOKED);
        Set<ExperiencePackage> packages = new HashSet<>(packageRepository.findAllById(packageIds));
        reservation.setPackages(packages);
        BigDecimal totalPrice = calculatePrice(table, packages);
        reservation.setPrice(totalPrice);

        return reservationRepository.save(reservation);
    }

    public boolean isWithinBusinessHour(LocalDateTime startTime, LocalDateTime endTime, Restaurant restaurant){
        LocalDate reservationDate = startTime.toLocalDate();
        LocalDateTime open = reservationDate.atTime(restaurant.getOpeningTime());
        LocalDateTime close = reservationDate.atTime(restaurant.getClosingTime());
        if(close.isBefore(open)){
            close = close.plusDays(1);
        }

        return startTime.isAfter(open) && endTime.isBefore(close);
    }

    public BigDecimal calculatePrice(RestaurantTable table, Set<ExperiencePackage> packages){
        BigDecimal price = new BigDecimal(String.valueOf(table.getPremiumCharge()));
        for(ExperiencePackage pack : packages){
            price = price.add(pack.getPrice());
        }
        return price;
    }
}
