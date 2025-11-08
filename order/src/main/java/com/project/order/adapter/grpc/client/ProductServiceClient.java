package com.project.order.adapter.grpc.client;

import com.project.order.ProductServiceOuterClass;
import com.project.order.configuration.GrpcClientConfig;
import com.project.order.adapter.grpc.dto.ProductResponseDto;
import com.project.order.domain.exception.ResourceNotFoundException;
import com.project.order.infrastructure.port.ProductServicePort;
import com.project.order.adapter.restApi.dto.error.ErrorDetail;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceClient implements ProductServicePort {
    private final GrpcClientConfig grpcClientConfig;

    public ProductServiceClient(GrpcClientConfig grpcClientConfig) {
        this.grpcClientConfig = grpcClientConfig;
    }

    @Override
    public ProductResponseDto getProductById(String productId) {
        var request = ProductServiceOuterClass.ProductRequest.newBuilder().setId(productId).build();
        ProductServiceOuterClass.ProductResponse product;
        try {
            product = grpcClientConfig.getProductServiceBlockingStub().getProductById(request);
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
