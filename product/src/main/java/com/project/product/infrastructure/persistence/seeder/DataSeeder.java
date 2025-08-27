package com.project.product.infrastructure.persistence.seeder;

import com.project.product.business.entity.Category;
import com.project.product.business.entity.Product;
import com.project.product.infrastructure.persistence.repository.CategoryRepository;
import com.project.product.infrastructure.persistence.repository.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("dev")
public class DataSeeder {
        @Bean
        CommandLineRunner seedDatabase(CategoryRepository categoryRepo, ProductRepository productRepository) {
                return args -> {

                        if (categoryRepo.count() == 0) {
                                Category electronics = new Category("Electronics", "DevicesOther");
                                Category drinksAndFood = new Category("DrinksAndFood", "Fastfood");
                                Category homeAndKitchen = new Category("HomeAndKitchen", "Countertops");
                                Category beauty = new Category("Beauty and personal care", "SelfImprovement");
                                Category sports = new Category("Sport & Outdoor", "SportsBaseball");
                                Category petSupplies = new Category("Pet Supplies", "Pets");
                                Category games = new Category("Games & Hobbies", "VideogameAsset");

                                categoryRepo.saveAll(
                                                List.of(electronics, drinksAndFood, homeAndKitchen, beauty, sports,
                                                                petSupplies, games));

                                System.out.println("Sample categories seeded.");
                        } else {
                                System.out.println("Category already seeded. Skipping sample data.");
                        }

                        if (productRepository.count() == 0) {
                                Category category = categoryRepo.findAll().getFirst();
                                if (category == null) {
                                        throw new RuntimeException("Category not found");
                                }
                                List<Product> products = Arrays.asList(
                                                new Product("Wireless Mouse", "Ergonomic wireless mouse", 19.99, 0.1f,
                                                                100,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/mouse1.jpg"), category),

                                                new Product("Bluetooth Headphones",
                                                                "Noise-cancelling over-ear headphones", 89.99, 0.15f,
                                                                50,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/headphones1.jpg"),
                                                                category),

                                                new Product("Smart LED Bulb", "Color-changing WiFi bulb", 12.49, 0.2f,
                                                                150,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/bulb1.jpg"), category),

                                                new Product("USB-C Hub", "Multiport adapter with HDMI and USB", 24.95,
                                                                0.05f, 80,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/hub1.jpg"), category),

                                                new Product("Laptop Stand", "Adjustable aluminum stand", 29.99, 0.1f,
                                                                60,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/stand1.jpg"), category),

                                                new Product("Fitness Tracker",
                                                                "Waterproof fitness band with heart monitor", 49.99,
                                                                0.2f, 75,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/tracker1.jpg"), category),

                                                new Product("Portable Speaker", "Water-resistant Bluetooth speaker",
                                                                39.95, 0.15f, 90,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/speaker1.jpg"), category),

                                                new Product("Webcam 1080p", "Full HD USB webcam", 59.00, 0.05f, 45,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/webcam1.jpg"), category),

                                                new Product("External SSD 1TB", "Fast portable SSD storage", 99.99,
                                                                0.25f, 30,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/ssd1.jpg"), category),

                                                new Product("Wireless Charger", "Qi-certified fast charger", 18.75,
                                                                0.1f, 110,
                                                                "thisIsARandomId",
                                                                List.of("https://example.com/charger1.jpg"), category));

                                productRepository.saveAll(products);
                                System.out.println("Sample products seeded.");
                        }
                };
        }
}
