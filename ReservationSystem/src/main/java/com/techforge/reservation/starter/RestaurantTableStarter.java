package com.techforge.reservation.starter;

import com.techforge.reservation.entity.Restaurant;
import com.techforge.reservation.entity.RestaurantTable;
import com.techforge.reservation.repository.RestaurantRepository;
import com.techforge.reservation.repository.RestaurantTableRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalTime;
import java.util.List;

@Component
public class RestaurantTableStarter implements ApplicationRunner {

    private final RestaurantTableRepository tableRepository;

    private final RestaurantRepository restaurantRepository;

    private final ObjectMapper mapper;

    public RestaurantTableStarter(RestaurantTableRepository tableRepository, RestaurantRepository restaurantRepository, ObjectMapper mapper) {
        this.tableRepository = tableRepository;
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(tableRepository.count()==0){
            Restaurant restaurant = new Restaurant();
            restaurant.setName("L'Oiseau Blanc");
            restaurant.setAddress("Paris");
            restaurant.setOpeningTime(LocalTime.of(8,0));
            restaurant.setClosingTime(LocalTime.of(22, 0));
            restaurantRepository.save(restaurant);
            ClassPathResource resource = new ClassPathResource("tables.json");
            String content = new String(resource.getInputStream().readAllBytes());
            List<RestaurantTable> tables = mapper.readValue(content, new TypeReference<List<RestaurantTable>>() {});
            for(RestaurantTable table : tables){
                table.setRestaurant(restaurantRepository.getReferenceById(table.getRestaurant().getId()));
            }
            tableRepository.saveAll(tables);
        }
    }
}
