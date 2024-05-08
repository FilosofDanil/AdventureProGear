package com.example.adventureprogearjava.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "product_attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAttribute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_attr_seq")
    @SequenceGenerator(name = "product_attr_seq", sequenceName = "product_attr_seq", allocationSize = 1)
    @Override
    public Long getId() {
        return super.getId();
    }

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @Column(name = "size")
    String size;

    @Column(name = "color")
    String color;

    @Column(name = "additional")
    String additional;

    @Column(name = "price_deviation", nullable = false)
    Long priceDeviation;

    @Column(name = "quantity", nullable = false)
    Long quantity;

    @OneToMany(mappedBy = "productAttribute")
    List<OrdersList> ordersLists;

}
