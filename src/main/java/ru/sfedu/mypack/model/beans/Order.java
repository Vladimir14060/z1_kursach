package ru.sfedu.mypack.model.beans;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

    @CsvBindByName
    private long id;

    @CsvBindByName
    private Customer customer;

    @CsvBindByName
    private Product product;

    @CsvBindByName
    private Double productPrice;

    @CsvBindByName
    private Double deliveryPrice;

    @CsvBindByName
    private boolean isFinished;

    public Order(long id, Customer customer, Product product, Double productPrice, Double deliveryPrice, boolean isFinished) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.productPrice = productPrice;
        this.deliveryPrice = deliveryPrice;
        this.isFinished = isFinished;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = System.currentTimeMillis();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && isFinished == order.isFinished && Objects.equals(customer, order.customer) && Objects.equals(product, order.product) && Objects.equals(productPrice, order.productPrice) && Objects.equals(deliveryPrice, order.deliveryPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, product, productPrice, deliveryPrice, isFinished);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id + '\'' +
                ", customer=" + customer + '\'' +
                ", product=" + product + '\'' +
                ", productPrice=" + productPrice + '\'' +
                ", deliveryPrice=" + deliveryPrice + '\'' +
                ", isFinished=" + isFinished + '\'' +
                '}';
    }
}
