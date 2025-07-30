package com.project.product.business.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;
import java.util.List;

@Document(collection = "products")
@Getter
@Setter
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private float discount;
    private int quantity;
    private float rating;

    @Field("created_at")
    private final Date createdAt = new Date();
    @Field("shop_id")
    private String shopId;
    @Field("image_links")
    private List<String> imageLinks;
    @Field("sold_count")
    private int soldCount = 0;

    @DBRef
    private Category category;

    public Product() {}

    public Product(String name, String description, double price, float discount, int quantity,
                   String shop_id, List<String> images_link, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.shopId = shop_id;
        this.imageLinks = images_link;
        this.quantity = quantity;
        this.category = category;
        this.discount = discount;
    }
}
