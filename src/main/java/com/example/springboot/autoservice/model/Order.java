package com.example.springboot.autoservice.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
    @OneToMany
    private List<Proposal> proposals;
    @OneToMany
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal orderPrice;
    @Temporal(TemporalType.DATE)
    private Date completionDate;
}
