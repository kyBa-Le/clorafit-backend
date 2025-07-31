package com.project.product.persistence.repository;

import com.project.product.business.entity.Product;
import com.project.product.business.model.ProductSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Repository
public class CustomProductRepository {

    private final MongoTemplate mongoTemplate;

    public CustomProductRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Product> searchProductsByCategory(ProductSearchCriteria criteria) {
        Query query = new Query();

        List<Criteria> filter = new ArrayList<>();
        Optional.ofNullable(criteria.getCategory()).ifPresent((category) -> {
            filter.add(Criteria.where("category").is(category));
        });
        Optional.ofNullable(criteria.getName()).ifPresent((name) -> {
            filter.add(
                    Criteria.where("name").regex(".*" + Pattern.quote(name) + ".*", "i")
            );
        });

        if (!filter.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(filter.toArray(new Criteria[0])));
        }

        query.with(PageRequest.of(criteria.getPage(), criteria.getSize(), criteria.getDirection(), criteria.getSort()));
        long total = mongoTemplate.count(query, Product.class);

        List<Product> products = mongoTemplate.find(query, Product.class);

        return new PageImpl<>(products, PageRequest.of(criteria.getPage(), criteria.getSize()), total);
    }
}
