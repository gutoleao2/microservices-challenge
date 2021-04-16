package com.williams.pagamento.repository;

import com.williams.pagamento.entity.Product;
import com.williams.pagamento.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
