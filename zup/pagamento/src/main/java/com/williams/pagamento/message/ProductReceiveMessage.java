package com.williams.pagamento.message;

import com.williams.pagamento.data.vo.ProductVO;
import com.williams.pagamento.entity.Product;
import com.williams.pagamento.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductReceiveMessage {

    private final ProductRepository productRepository;

    @Autowired
    public ProductReceiveMessage(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive (@Payload ProductVO productVO) {

        productRepository.save(Product.create(productVO));
    }
}
