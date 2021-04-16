package com.williams.pagamento.services;

import com.williams.pagamento.data.vo.SaleVO;
import com.williams.pagamento.entity.ProductSale;
import com.williams.pagamento.entity.Sale;
import com.williams.pagamento.exception.ResourceNotFoundException;
import com.williams.pagamento.repository.ProductRepository;
import com.williams.pagamento.repository.ProductSaleRepository;
import com.williams.pagamento.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductSaleRepository productSaleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, ProductSaleRepository productSaleRepository) {
        this.saleRepository = saleRepository;
        this.productSaleRepository = productSaleRepository;
    }

    public SaleVO create(SaleVO saleVO){
        Sale sale = saleRepository.save(Sale.create(saleVO));

        List<ProductSale> productSaleList = new ArrayList<>();

        saleVO.getProducts().forEach(p -> {
            ProductSale pS = ProductSale.create(p);
            pS.setSale(sale);
            productSaleList.add(productSaleRepository.save(pS));
        });

        sale.setProducts(productSaleList);

        return SaleVO.create(sale);
    }

    public Page<SaleVO> findAll(Pageable pageable) {
        var page = saleRepository.findAll(pageable);
        return page.map(this::convertToSaleVO);
    }

    private SaleVO convertToSaleVO(Sale sale) {
        return SaleVO.create(sale);
    }

    public SaleVO findById(Long id) {
        var entity = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("The product with id {0} was not found", id)));

        return SaleVO.create(entity);
    }

}
