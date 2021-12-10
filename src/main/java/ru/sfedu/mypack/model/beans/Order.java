package ru.sfedu.mypack.model.beans;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import ru.sfedu.mypack.model.converter.CustomerConverter;
import ru.sfedu.mypack.model.converter.ProductConverter;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

    @CsvBindByName
    private long id;

    @CsvCustomBindByName(converter = CustomerConverter.class)
    private Customer customer;

    @CsvCustomBindByName(converter = ProductConverter.class)
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

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId() {
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
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() && isFinished() == order.isFinished() && Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(getProduct(), order.getProduct()) && Objects.equals(getProductPrice(), order.getProductPrice()) && Objects.equals(getDeliveryPrice(), order.getDeliveryPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getProduct(), getProductPrice(), getDeliveryPrice(), isFinished());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id + '\'' +
                ", customer=" + customer + '\'' +
                ", product=" + product + '\'' +
                ", productPrice=" + productPrice + '\'' +
                ", deliveryPrice=" + deliveryPrice + '\'' +
                ", isFinished=" + isFinished +
                '}';
    }
}
