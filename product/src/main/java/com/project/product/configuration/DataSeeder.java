package com.project.product.configuration;

import com.project.product.business.entity.Category;
import com.project.product.persistence.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev")
public class DataSeeder {
    @Bean
    CommandLineRunner seedDatabase(CategoryRepository categoryRepo) {
        return args -> {

            if (categoryRepo.count() == 0 ) {
                Category electronics = new Category("Electronics", "DevicesOtherIcon");
                Category drinksAndFood = new Category("DrinksAndFood", "FastfoodIcon");
                Category homeAndKitchen = new Category("HomeAndKitchen", "CountertopsIcon");
                Category beauty = new Category("Beauty and personal care", "SelfImprovementIcon");
                Category sports = new Category("Sport & Outdoor", "SportsBaseballIcon");
                Category petSupplies = new Category("Pet Supplies", "PetsIcon");
                Category games = new Category("Games & Hobbies", "VideogameAssetIcon");


                categoryRepo.saveAll(
                        List.of(electronics, drinksAndFood, homeAndKitchen, beauty, sports, petSupplies, games)
                );

                System.out.println("Sample categories seeded.");
            } else {
                System.out.println("Database already seeded. Skipping sample data.");
            }
        };
    }
}
