package com.oguz.springrediscache.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String serialNumber;
  private long quantity;

  public Product(long id, String name, String serialNumber, long quantity) {
    this.id = id;
    this.name = name;
    this.serialNumber = serialNumber;
    this.quantity = quantity;
  }

  public Product() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("name", name)
        .add("serialNumber", serialNumber)
        .add("quantity", quantity)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return id == product.id && quantity == product.quantity && Objects.equal(name, product.name) && Objects.equal(serialNumber, product.serialNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, name, serialNumber, quantity);
  }
}
