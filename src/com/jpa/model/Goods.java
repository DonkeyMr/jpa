package com.jpa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_goods")
@Data
public class Goods {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "t_category_goods",
            joinColumns = {@JoinColumn(name = "goods_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
    )
    private Set<Category> categories = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Goods goods = (Goods) o;

        if (id != null ? !id.equals(goods.id) : goods.id != null) return false;
        return name != null ? name.equals(goods.name) : goods.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
