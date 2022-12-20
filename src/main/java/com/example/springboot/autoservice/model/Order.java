package com.example.springboot.autoservice.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Car car;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date acceptanceDate;
    @OneToMany(mappedBy = "order")
    private List<Proposal> proposals;
    @ManyToMany
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal orderPrice;
    @Temporal(TemporalType.DATE)
    private Date completionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(car, order.car) && Objects.equals(description, order.description) && Objects.equals(acceptanceDate, order.acceptanceDate) && Objects.equals(proposals, order.proposals) && Objects.equals(products, order.products) && orderStatus == order.orderStatus && Objects.equals(orderPrice, order.orderPrice) && Objects.equals(completionDate, order.completionDate);
    }


    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", car=" + car
                + ", description='" + description + '\''
                + ", acceptanceDate=" + acceptanceDate
                + ", proposals=" + proposals
                + ", products=" + products
                + ", orderStatus=" + orderStatus
                + ", orderPrice=" + orderPrice
                + ", completionDate=" + completionDate
                + '}';
    }
}
