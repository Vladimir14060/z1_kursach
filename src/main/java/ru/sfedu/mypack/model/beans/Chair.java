package ru.sfedu.mypack.model.beans;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mypack.model.enums.EnumCategory;
import ru.sfedu.mypack.model.enums.EnumLeg;

import java.util.Objects;

@Root(name = "Chair")
public class Chair extends Product {

    @Element
    @CsvBindByName
    private String wood;

    @Element
    @CsvBindByName
    private Double height;

    @Element
    @CsvBindByName
    private Double weight;

    @Element
    @CsvBindByName
    private EnumLeg leg;

    public Chair(long id, String name, EnumCategory category, Double price, String wood, Double chairHeight, Double chairWeight, EnumLeg leg){
       super(id, name, category, price);
        this.wood = wood;
        this.height = chairHeight;
        this.weight = chairWeight;
        this.leg = leg;
    }

    public Chair(){}

    public String getWood() {
        return wood;
    }

    public void setWood(String wood) {
        this.wood = wood;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public EnumLeg getLeg() {
        return leg;
    }

    public void setLeg(EnumLeg leg) {
        this.leg = leg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Chair chair = (Chair) o;
        return Objects.equals(wood, chair.wood) && Objects.equals(height, chair.height) && Objects.equals(weight, chair.weight) && leg == chair.leg;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wood, height, weight, leg);
    }

    @Override
    public String toString() {
        return "Chair{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price + '\'' +
                ", wood='" + wood + '\'' +
                ", height=" + height + '\'' +
                ", weight=" + weight + '\'' +
                ", leg=" + leg + '\'' +
                '}';
    }
}
