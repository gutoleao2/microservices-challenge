package com.williams.pagamento.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.williams.pagamento.entity.Product;
import com.williams.pagamento.entity.Sale;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;

@JsonPropertyOrder({"id", "date", "products", "totalPrice"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductSaleVO extends RepresentationModel<ProductSaleVO> implements Serializable {

    private static final long serialVersionUID = 1086924036356142019L;
    @JsonProperty("id")
    private Long id;

    @JsonProperty("idProduct")
    private Long idProduct;

    @JsonProperty("amount")
    private Integer amount;

    public static ProductSaleVO create(ProductSaleVO productSaleVO) {
        return new ModelMapper().map(productSaleVO, ProductSaleVO.class);
    }
}
