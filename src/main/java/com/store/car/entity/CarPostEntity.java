package com.store.car.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
public class CarPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "engine")
    private String engine;

    @Column(name = "city")
    private String city;

    @Column(name = "createdAt")
    private String createdAt;

    @Column(name = "contact")
    private String contact;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false, referencedColumnName = "d")
    private OwnerPostEntity ownerPost;
}
