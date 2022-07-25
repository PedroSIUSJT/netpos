package br.com.netpos.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Table
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long code;
    private String name;
    private BigDecimal price;
    private Integer inventory;

    @ManyToOne
    @JoinColumn(name="fk_user")
    private User userProduct;
}
