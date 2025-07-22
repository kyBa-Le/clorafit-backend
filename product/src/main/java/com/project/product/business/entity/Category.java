package com.project.product.business.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    private String id;
    private String name;
    private String icon;

    public Category(String id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public Category(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }
}
