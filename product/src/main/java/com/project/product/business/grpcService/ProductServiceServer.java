package com.project.product.business.grpcService;

import com.project.product.ProductServiceGrpc;
import com.project.product.ProductServiceOuterClass;
import com.project.product.infrastructure.persistence.repository.ProductRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceServer extends ProductServiceGrpc.ProductServiceImplBase {
    private final ProductRepository productRepository;

    public ProductServiceServer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void getProductById(ProductServiceOuterClass.ProductRequest request,
                               StreamObserver<ProductServiceOuterClass.ProductResponse> responseObserver) {
        var id = request.getId();
        var optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("No product found with id: " + id)
                    .asRuntimeException());
        } else {
            var product = optionalProduct.get();
            var response = ProductServiceOuterClass.ProductResponse.newBuilder()
                    .setId(id)
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .setCategoryId(product.getCategory().getId())
                    .setQuantity(product.getQuantity())
                    .setDiscount(product.getDiscount())
                    .setShopId(product.getShopId())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
