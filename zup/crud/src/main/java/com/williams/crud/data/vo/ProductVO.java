package com.williams.crud.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.williams.crud.entity.Product;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@JsonPropertyOrder({"id", "name", "stock", "price"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductVO extends RepresentationModel<ProductVO> implements Serializable {

    private static final long serialVersionUID = -8274544195208618609L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("price")
    private Double price;

    public static ProductVO create(Product product) {
        return new ModelMapper().map(product, ProductVO.class);
    }
}
