package com.williams.pagamento.entity;

import com.williams.pagamento.data.vo.ProductSaleVO;
import com.williams.pagamento.data.vo.ProductVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_sale")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductSale implements Serializable {

    private static final long serialVersionUID = 9041826767963107953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_product", nullable = false, length = 10)
    private Long idProduct;

    @Column(name = "amount", nullable = false, length = 10)
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sale")
    private Sale sale;

    public static ProductSale create(ProductSaleVO productSaleVO) {
        return new ModelMapper().map(productSaleVO, ProductSale.class);
    }
}
