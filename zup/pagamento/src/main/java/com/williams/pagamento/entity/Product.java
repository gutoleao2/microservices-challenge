package com.williams.pagamento.entity;

import com.williams.pagamento.data.vo.ProductVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable {

    private static final long serialVersionUID = 7685507133380041111L;

    @Id
    private Long id;

    @Column(name = "stock", nullable = false, length = 10)
    private Integer stock;

    public static Product create(ProductVO productVO) {
        return new ModelMapper().map(productVO, Product.class);
    }
}
