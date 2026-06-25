package com.techforge.reservation.starter;

import com.techforge.reservation.entity.ExperiencePackage;
import com.techforge.reservation.repository.ExperiencePackageRepository;
import com.techforge.reservation.repository.RestaurantRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class ExperienceStarter implements ApplicationRunner {

    private final ExperiencePackageRepository packageRepository;

    private final RestaurantRepository restaurantRepository;

    private final ObjectMapper mapper;

    public ExperienceStarter(ExperiencePackageRepository packageRepository, RestaurantRepository restaurantRepository, ObjectMapper mapper) {
        this.packageRepository = packageRepository;
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (packageRepository.count() == 0) {
            ClassPathResource resource = new ClassPathResource("experiences.json");
            String contents = new String(resource.getInputStream().readAllBytes());
            List<ExperiencePackage> packages = mapper.readValue(contents, new TypeReference<List<ExperiencePackage>>() {
            });
            for (ExperiencePackage pack : packages) {
                pack.setRestaurant(restaurantRepository.getReferenceById(pack.getRestaurant().getId()));
            }
            packageRepository.saveAll(packages);
        }
    }
}
