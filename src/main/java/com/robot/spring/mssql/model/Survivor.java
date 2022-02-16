package com.robot.spring.mssql.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "survivorsxx")
public class Survivor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "survId")
    private Set<Location> place;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "suride")
    private Set<Resource> resource;


    private String age;
    private String gender;
    private String infectionstatus;
    private int infectionReport;

    public Survivor() {

    }

    public Survivor( String name, Set<Location> place, Set<Resource> resource, String age, String gender, String infectionstatus, int infectionReport) {

        this.name = name;
        this.place = place;
        this.resource = resource;
        this.age = age;
        this.gender = gender;
        this.infectionstatus = infectionstatus;
        this.infectionReport = infectionReport;
    }

    //
//    public Survivor(int infectionReport, String age, String name, String infectionstatus, String gender) {
//    }

    public Set<Location> getPlace() {
        return place;
    }

    public void setPlace(Set<Location> place) {
        this.place = place;
    }

    public Set<Resource> getResource() {
        return resource;
    }

    public void setResource(Set<Resource> resource) {
        this.resource = resource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInfectionstatus() {
        return infectionstatus;
    }

    public void setInfectionstatus(String infectionstatus) {
        this.infectionstatus = infectionstatus;
    }

    public int getInfectionReport() {
        return infectionReport;
    }

    public void setInfectionReport(int infectionReport) {
        this.infectionReport = infectionReport;
    }

//    public String getPlace() {
//        return place;
//    }
//
//    public void setPlace(String place) {
//        this.place = place;
//    }


    @Override
    public String toString() {
        return "Survivor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place=" + place +
                ", resource=" + resource +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", infectionstatus='" + infectionstatus + '\'' +
                ", infectionReport=" + infectionReport +
                '}';
    }
}
