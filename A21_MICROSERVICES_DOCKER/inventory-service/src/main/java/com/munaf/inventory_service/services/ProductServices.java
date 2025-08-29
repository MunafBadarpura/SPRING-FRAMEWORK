package com.munaf.inventory_service.services;

import com.munaf.inventory_service.dtos.OrderDto;
import com.munaf.inventory_service.dtos.OrderItemDto;
import com.munaf.inventory_service.dtos.ProductDTO;
import com.munaf.inventory_service.entities.Product;
import com.munaf.inventory_service.exceptions.ResourceNotFoundException;
import com.munaf.inventory_service.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServices {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServices(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + productId));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Transactional
    public Double reduceStock(OrderDto orderDto) {
        double totalPrice = 0.0;
        for (OrderItemDto item : orderDto.getOrderItems()) {
            Long productId = item.getProductId();
            Integer quantity = item.getQuantity();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + productId));

            if (product.getStock() < quantity) throw new RuntimeException("Product quantity is more than our quantity");

            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
            totalPrice += product.getStock() * product.getPrice();
        }
        return totalPrice;
    }
}
