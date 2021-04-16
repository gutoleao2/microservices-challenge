package com.williams.pagamento.repository;

import com.williams.pagamento.entity.ProductSale;
import com.williams.pagamento.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
}
