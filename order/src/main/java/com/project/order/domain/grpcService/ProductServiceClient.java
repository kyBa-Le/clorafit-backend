package com.project.order.domain.grpcService;

import com.project.order.ProductServiceGrpc;
import com.project.order.ProductServiceOuterClass;
import com.project.order.domain.dto.ProductResponseDto;
import com.project.order.domain.exception.ResourceNotFoundException;
import com.project.order.infrastructure.port.ProductServicePort;
import com.project.order.presentation.dto.error.ErrorDetail;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceClient implements ProductServicePort {
    private final ProductServiceGrpc.ProductServiceBlockingStub blockingStub;

    public ProductServiceClient(ProductServiceGrpc.ProductServiceBlockingStub blockingStub) {
        this.blockingStub = blockingStub;
    }

    @Override
    public ProductResponseDto getProductById(String productId) {
        var request = ProductServiceOuterClass.ProductRequest.newBuilder().setId(productId).build();
        ProductServiceOuterClass.ProductResponse product;
        try {
            product = blockingStub.getProductById(request);
        } catch (io.grpc.StatusRuntimeException e) {
            ErrorDetail errorDetail = new ErrorDetail("productId", e.getStatus().getDescription());
            throw new ResourceNotFoundException("Product not found", errorDetail);
        }
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscount(),
                product.getShopId(),
                product.getQuantity(),
                product.getCategoryId()
        );
    }

}
