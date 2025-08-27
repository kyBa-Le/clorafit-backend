package com.project.order.configuration;

import com.project.order.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class GrpcClientConfig {
    private final DiscoveryClient discoveryClient;

    public GrpcClientConfig(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public ProductServiceGrpc.ProductServiceBlockingStub getProductServiceBlockingStub() {
        var instances = discoveryClient.getInstances("product-service");
        if (instances.isEmpty()) {
            throw new IllegalStateException("No instances of product-service found");
        }
        var productService = instances.getFirst();
        ManagedChannel channel = ManagedChannelBuilder.forAddress(productService.getHost(), 9090).usePlaintext().build();
        return ProductServiceGrpc.newBlockingStub(channel);
    }
}
