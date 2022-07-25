package br.com.netpos.gateway.h2;


import br.com.netpos.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameStartingWith(String name);
    Product findByProduct(Long code);
}
