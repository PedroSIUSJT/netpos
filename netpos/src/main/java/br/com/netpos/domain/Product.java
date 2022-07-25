package br.com.netpos.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
}
