package com.example.springboot.autoservice.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "owner")
    private List<Car> cars;
    @OneToMany
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id) && Objects.equals(cars, owner.cars)
                && Objects.equals(orders, owner.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cars, orders);
    }

    @Override
    public String toString() {
        return "Owner{"
                + "id=" + id
                + ", cars=" + cars
                + ", orders=" + orders
                + '}';
    }
}
