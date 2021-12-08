package ru.sfedu.mypack.model.beans;

import com.opencsv.bean.CsvBindByName;
import ru.sfedu.mypack.model.EnumCategory;
import ru.sfedu.mypack.model.EnumColourWool;

import java.util.Objects;

public class Carpet extends Product{

    @CsvBindByName
    private String wool;

    @CsvBindByName
    private Double weight;

    @CsvBindByName
    private Double length;

    @CsvBindByName
    private EnumColourWool colour;

    public Carpet(long id, String name, EnumCategory category, Double price, String wool, Double weight, Double length, EnumColourWool colour){
        super(id, name, category, price);
        this.wool = wool;
        this.weight = weight;
        this.length = length;
        this.colour = colour;
    }

    public Carpet(){}

    public String getWool() {
        return wool;
    }

    public void setWool(String wool) {
        this.wool = wool;
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

    public EnumColourWool getColour() {
        return colour;
    }

    public void setColour(EnumColourWool colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Carpet carpet = (Carpet) o;
        return Objects.equals(wool, carpet.wool) && Objects.equals(weight, carpet.weight) && Objects.equals(length, carpet.length) && colour == carpet.colour;
    }

    @Override
    public String toString() {
        return "Carpet{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price + '\'' +
                ", wool='" + wool + '\'' +
                ", weight=" + weight + '\'' +
                ", length=" + length + '\'' +
                ", colour=" + colour +
                '}';
    }
}
