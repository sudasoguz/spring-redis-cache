package com.oguz.springrediscache.service;

import com.oguz.springrediscache.entity.Product;
import com.oguz.springrediscache.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

@Service
public class ProductService implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(Logger.class);

  private final RedisTemplate redisTemplate;
  private final ProductRepository productRepository;

  public ProductService(RedisTemplate redisTemplate, ProductRepository productRepository) {
    this.redisTemplate = redisTemplate;
    this.productRepository = productRepository;
  }

  @Cacheable(key = "#product.id", cacheNames = "productCache")
  public Product save(Product product) {
    logger.info("Saved to db and cache.");
    return productRepository.save(product);
  }

  @Cacheable(key = "#productId", cacheNames = "productCache")
  public Product getProductById(long productId) {
    logger.info("Received from database.");
    Product product = productRepository.getProductById(productId);
    return product;
  }

  @CacheEvict(key = "#productId", cacheNames = "productCache")
  public void deleteProduct(long productId) {
    productRepository.deleteById(productId);
  }

  @CacheEvict(allEntries = true, cacheNames = "productCache")
  public String clearCache() {
    logger.info("Cache cleared");
    return "Cache cleared.";
  }

  public StringBuffer getAllKeys() {
    StringBuffer result = new StringBuffer();
    Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys("*".getBytes());
    Iterator<byte[]> it = keys.iterator();
    while (it.hasNext()) {
      byte[] data = (byte[]) it.next();
      result.append(new String(data, 0, data.length)).append(" - ");
    }
    return result;
  }

}
