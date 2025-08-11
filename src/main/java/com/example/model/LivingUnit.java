package com.example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LivingUnit {

    public enum UnitType {
        HOME, FLAT, APARTMENTS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UnitType unitType;
    private int numberOfRooms;
    private int floor;
    private BigDecimal cost;
    private String description;

    @OneToMany(mappedBy = "livingUnit", cascade = CascadeType.ALL, orphanRemoval = true)
    // To not get infinite json
    @JsonManagedReference
    private List<BookedPeriod> bookedPeriods = new ArrayList<>();

    public LivingUnit() {

    }

    public LivingUnit(long id, UnitType unitType, int numberOfRooms, int floor, BigDecimal cost, String description) {
        this.id = id;
        this.unitType = unitType;
        this.numberOfRooms = numberOfRooms;
        this.floor = floor;
        this.cost = cost;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookedPeriod> getBookedPeriods() {
        return bookedPeriods;
    }

    public void setBookedPeriods(List<BookedPeriod> bookedPeriods) {
        this.bookedPeriods = bookedPeriods;
    }

    public void addBookedPeriod(BookedPeriod bookedPeriod) {
        bookedPeriod.setLivingUnit(this);
        bookedPeriods.add(bookedPeriod);
    }
}
