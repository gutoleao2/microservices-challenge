package com.williams.crud.services;

import com.williams.crud.data.vo.ProductVO;
import com.williams.crud.entity.Product;
import com.williams.crud.exception.ResourceNotFoundException;
import com.williams.crud.message.ProductSendMessage;
import com.williams.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductSendMessage productSendMessage;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductSendMessage productSendMessage) {
        this.productRepository = productRepository;
        this.productSendMessage = productSendMessage;
    }

    public ProductVO create(ProductVO productVO) {
        ProductVO pVO = ProductVO.create(productRepository.save(Product.create(productVO)));
        productSendMessage.sendMessage(pVO);
        return pVO;
    }

    public Page<ProductVO> findAll(Pageable pageable) {
        var page = productRepository.findAll(pageable);
        return page.map(this::convertToProductVO);
    }

    private ProductVO convertToProductVO(Product product) {
        return ProductVO.create(product);
    }

    public ProductVO findById(Long id) {
        var entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("The product with id {0} was not found", id)));

        return ProductVO.create(entity);
    }

    public ProductVO update(ProductVO productVO) {
        final Optional<Product> optionalProduct = productRepository.findById(productVO.getId());
        if (!optionalProduct.isPresent()){
            new ResourceNotFoundException(MessageFormat.format("The product with id {0} was not found", productVO.getId()));
        }

        return ProductVO.create(productRepository.save(Product.create(productVO)));
    }

    public  void delete (Long id) {
        var entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("The product with id {0} was not found", id)));

        productRepository.delete(entity);
    }
}
