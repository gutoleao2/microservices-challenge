package com.williams.pagamento.entity;

import com.williams.pagamento.data.vo.SaleVO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Sale implements Serializable {

    private static final long serialVersionUID = -7319178803811656667L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false, length = 10)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = {CascadeType.REFRESH})
    private List<ProductSale> products;

    @Column(name = "total_price", nullable = false, length = 10)
    private Double totalPrice;

    public static Sale create(SaleVO saleVO) {
        return new ModelMapper().map(saleVO, Sale.class);
    }
}
