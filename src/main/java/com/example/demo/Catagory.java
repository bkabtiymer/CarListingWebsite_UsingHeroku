package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
public class Catagory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //    @NotNull
//    @Size(min = 2)
    @Column (name="name")
    private String name;


    @OneToMany(mappedBy = "catagory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    public Set<Car> cars;


    public Catagory() {
    }

    public Catagory(String name, Set<Car> cars) {
        this.name = name;
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}


