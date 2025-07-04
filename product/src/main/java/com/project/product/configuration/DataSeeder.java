package com.project.product.configuration;

import com.project.product.business.entity.Category;
import com.project.product.persistence.repository.CategoryRepository;
import com.project.product.persistence.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev")
public class DataSeeder {
    @Bean
    CommandLineRunner seedDatabase(CategoryRepository categoryRepo, ProductRepository productRepo) {
        return args -> {

            if (categoryRepo.count() == 0 && productRepo.count() == 0) {
                Category electronics = new Category(null, "Electronics");
                Category clothing = new Category(null, "Clothing");

                categoryRepo.saveAll(List.of(electronics, clothing));

                System.out.println("Sample categories and products seeded.");
            } else {
                System.out.println("Database already seeded. Skipping sample data.");
            }
        };
    }
}
