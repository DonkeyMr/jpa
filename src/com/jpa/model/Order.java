package com.jpa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_order")
@Cacheable
@Data
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "order_name")
    private String orderName;

    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (orderName != null ? !orderName.equals(order.orderName) : order.orderName != null) return false;
        return amount != null ? amount.equals(order.amount) : order.amount == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (orderName != null ? orderName.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
