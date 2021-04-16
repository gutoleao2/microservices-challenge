package com.williams.pagamento.data.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.williams.pagamento.entity.ProductSale;
import com.williams.pagamento.entity.Sale;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "date", "products", "totalPrice"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SaleVO extends RepresentationModel<SaleVO> implements Serializable {

    private static final long serialVersionUID = -8791726342587688467L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("products")
    private List<ProductSaleVO> products;

    @JsonProperty("totalPrice")
    private Double totalPrice;

    public static SaleVO create(Sale sale) {
        return new ModelMapper().map(sale, SaleVO.class);
    }
}
