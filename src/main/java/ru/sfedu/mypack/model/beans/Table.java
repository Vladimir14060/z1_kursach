package ru.sfedu.mypack.model.beans;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.mypack.model.enums.EnumCategory;
import ru.sfedu.mypack.model.enums.EnumLeg;

import java.util.Objects;

@Root(name = "Table")
public class Table extends Product {

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
    private Double length;

    @Element
    @CsvBindByName
    private EnumLeg leg;

    public Table(long id, String name, EnumCategory category, Double price, String wood, Double height, Double weight, Double length, EnumLeg leg){
        super(id, name, category, price);
        this.wood = wood;
        this.height = height;
        this.weight = weight;
        this.length = length;
        this.leg = leg;
    }

    public Table(){}

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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
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
        Table table = (Table) o;
        return Objects.equals(wood, table.wood) && Objects.equals(height, table.height) && Objects.equals(weight, table.weight) && Objects.equals(length, table.length) && leg == table.leg;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wood, height, weight, length, leg);
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price + '\'' +
                ", wood='" + wood + '\'' +
                ", height=" + height + '\'' +
                ", weight=" + weight + '\'' +
                ", length=" + length + '\'' +
                ", leg=" + leg +
                '}';
    }
}
