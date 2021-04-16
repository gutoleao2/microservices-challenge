package com.williams.crud.entity;

import com.williams.crud.data.vo.ProductVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable {

    private static final long serialVersionUID = -3353926433694836650L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 255)
    private String name;

    @Column(name="stock", nullable = false, length = 10)
    private Integer stock;

    @Column(name="price", nullable = false, length = 10)
    private Double price;

    public static Product create(ProductVO productVO) {
        return new ModelMapper().map(productVO, Product.class);
    }
}
