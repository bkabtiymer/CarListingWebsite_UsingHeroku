package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="car_id")
    private long id;

    @NotNull
    @Size(min=2)
    @Column (name="manufacturer")
    private String manufacturer;

    @NotNull
    @Size(min=2)
    @Column (name="model")
    private String model;

    @Column (name="year")
    private int year;

    @NotNull
    @Size(min=2)
    @Column (name="msrp")
    private String msrp;

    @NotNull
    @Size(min=2)
    @Column (name="image")
    private String image;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "catagory_id")
    private Catagory catagory;


    public Car() {
    }

    public Car(String manufacturer, String model, int year, String msrp, String image, Catagory catagory) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.msrp = msrp;
        this.image = image;
        this.catagory = catagory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getMsrp() {
        return msrp;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }
}
