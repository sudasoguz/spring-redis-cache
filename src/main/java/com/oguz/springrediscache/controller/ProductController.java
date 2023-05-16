package com.oguz.springrediscache.controller;

import com.oguz.springrediscache.entity.Product;
import com.oguz.springrediscache.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
public class ProductController {

  private static final Logger logger = LoggerFactory.getLogger(Logger.class);

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<Product> saveProduct(@RequestBody Product product){
    return ResponseEntity.ok(productService.save(product));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") long productId){
    return ResponseEntity.ok(productService.getProductById(productId));
  }

  @GetMapping("/clearCache")
  public ResponseEntity<String> clearCache() {
    return ResponseEntity.ok(productService.clearCache());
  }

  @GetMapping("/getProductKeysFromCache")
  public ResponseEntity<StringBuffer> getProductKeysFromCache(){
    return ResponseEntity.ok(productService.getAllKeys());
  }
}
