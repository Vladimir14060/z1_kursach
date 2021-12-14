package ru.sfedu.mypack.model.beans;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mypack.model.enums.EnumCategory;


import java.io.Serializable;
import java.util.Objects;

@Root(name = "Product")
public class Product implements Serializable {

    @Attribute
    @CsvBindByName
    public long id;

    @Element
    @CsvBindByName
    public String name;

    @Element
    @CsvBindByName
    public EnumCategory category;

    @Element
    @CsvBindByName
    public Double price;

    public Product(long id, String name, EnumCategory category, Double price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product(){}

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setId() {
        this.id = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumCategory getCategory() {
        return category;
    }

    public void setCategory(EnumCategory category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && category == product.category && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category + '\'' +
                ", price=" + price +
                '}';
    }

}
