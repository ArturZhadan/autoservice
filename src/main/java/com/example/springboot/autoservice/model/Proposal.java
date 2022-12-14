package com.example.springboot.autoservice.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "proposals")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    private Worker worker;
    private BigDecimal proposalPrice;
    @Enumerated(EnumType.STRING)
    private ProposalStatus proposalStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public BigDecimal getProposalPrice() {
        return proposalPrice;
    }

    public void setProposalPrice(BigDecimal proposalPrice) {
        this.proposalPrice = proposalPrice;
    }

    public ProposalStatus getProposalStatus() {
        return proposalStatus;
    }

    public void setProposalStatus(ProposalStatus proposalStatus) {
        this.proposalStatus = proposalStatus;
    }

    @Override
    public String toString() {
        return "Proposal{"
                + "id=" + id
                + ", order=" + order
                + ", worker=" + worker
                + ", proposalPrice=" + proposalPrice
                + ", proposalStatus=" + proposalStatus
                + '}';
    }
}
