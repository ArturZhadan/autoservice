package com.example.springboot.autoservice.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private String model;
    private int year;
    private String number;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return year == car.year && Objects.equals(id, car.id)
                && Objects.equals(manufacturer, car.manufacturer)
                && Objects.equals(model, car.model)
                && Objects.equals(number, car.number)
                && Objects.equals(owner, car.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, model, year, number, owner);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", manufacturer='" + manufacturer + '\''
                + ", model='" + model + '\''
                + ", year=" + year
                + ", number='" + number + '\''
                + ", owner=" + owner
                + '}';
    }
}
