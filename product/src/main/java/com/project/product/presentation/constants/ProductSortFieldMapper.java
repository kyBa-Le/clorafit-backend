package com.project.product.presentation.constants;


import java.util.*;

public class ProductSortFieldMapper {
    public static final Map<String, String> SORT_FIELDS;

    static {
        Map<String, String> sortFields = new HashMap<>();
        sortFields.put("", "");
        sortFields.put("sold", "soldCount");
        sortFields.put("latest", "createdAt");
        sortFields.put("rating", "rating");
        sortFields.put("price", "price");
        SORT_FIELDS = Collections.unmodifiableMap(sortFields);
    }

    public static List<String> getSortFields() {
        return new ArrayList<>(SORT_FIELDS.keySet());
    }
}
