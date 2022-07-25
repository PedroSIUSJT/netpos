package br.com.netpos.api;

import br.com.netpos.domain.Product;
import br.com.netpos.domain.User;
import br.com.netpos.gateway.h2.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Max;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value= "/product")
@RequestMapping("/product")
public class ProductApi {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @ApiOperation(value = "Busca todos os produtos")
    public ResponseEntity<List<Product>> listProducts(@RequestParam(required = false) final String name, @RequestHeader("x-user") Long user){
        if(name != null){
            return ResponseEntity.ok(productRepository.findByNameStartingWithAndUserProductId(name, user));
        }
        List<Product> products = productRepository.findByUserProductId(user);
        if (products.isEmpty()){
          return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }
    @GetMapping("/{code}")
    @ApiOperation(value = "Busca produto por código")
    public ResponseEntity<Product> getByCode(@PathVariable("code") final Long code){
        final Optional<Product> product = productRepository.findById(code);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um produto", notes = "Preencher:\n- name\n- price\n- inventory")
    public ResponseEntity<Product> createProduct(@RequestBody final ProductRequest productRequest) {
        if (inventoryExcededThousand(productRequest.getInventory())){
            ResponseEntity.status(HttpStatus.CONFLICT).body("inventory can't be bigger than");
        }
        final Product productCreated = productRepository.save(ProductRequest.toProduct(productRequest));
        return ResponseEntity.ok(productCreated);
    }

    @PutMapping("/{code}")
    @ApiOperation(value = "Altera as informações de um produto específico", notes = "Preencher:\n- name\n- price\n- inventory")
    public ResponseEntity<Product> editProduct(@PathVariable("code") final Long code, @RequestBody final UpdateProductRequest updateProductRequest){
        if (inventoryExcededThousand(updateProductRequest.getInventory())){
            ResponseEntity.status(HttpStatus.CONFLICT).body("inventory can't be bigger than");
        }
        return ResponseEntity.ok(productRepository.save(UpdateProductRequest.toProduct(updateProductRequest, code)));
    }

    private Boolean inventoryExcededThousand(final Integer inventory) {
        return inventory > 1000;
    }
}

@Data
class ProductRequest {
    private String name;
    private BigDecimal price;
    @Max(value = 1000)
    private Integer inventory;
    private Long userProduct;

    public static Product toProduct(final ProductRequest productRequest) {
        User userProduct = new User();
        userProduct.setId(productRequest.getUserProduct());

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setInventory(productRequest.getInventory());
        product.setUserProduct(userProduct);
        return product;
    }
}

@Data
@AllArgsConstructor
class UpdateProductRequest {
    private String name;
    private BigDecimal price;
    private Integer inventory;

    public static Product toProduct(final UpdateProductRequest productRequest, @PathVariable("code") final Long code) {
        Product product = new Product();
        product.setCode(code);
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setInventory(productRequest.getInventory());
        return product;
    }
}
