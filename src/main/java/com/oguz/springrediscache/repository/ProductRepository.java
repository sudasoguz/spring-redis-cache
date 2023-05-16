package com.oguz.springrediscache.repository;

import com.oguz.springrediscache.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Product getProductById(long productId);

}
