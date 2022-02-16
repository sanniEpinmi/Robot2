package com.robot.spring.mssql.model;

import javax.persistence.*;

@Entity
@Table(name = "Resourcexxx")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String water;
    private String food;
    private String medication;
    private String  ammunition;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(String ammunition) {
        this.ammunition = ammunition;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", water='" + water + '\'' +
                ", food='" + food + '\'' +
                ", medication='" + medication + '\'' +
                ", ammunition='" + ammunition + '\'' +

                '}';
    }



}
