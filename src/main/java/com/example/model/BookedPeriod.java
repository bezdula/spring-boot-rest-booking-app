package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

import java.time.LocalDate;

// TODO: consider using separate DTO for booking period
@Entity
public class BookedPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient // TODO: should it be Transient?
    private Long livingUnitId;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    // Use column "living_unit_id" as a Foreign Key (?)
    @JoinColumn(name = "living_unit_id", nullable = false)
    // To not get infinite json
    @JsonBackReference
    private LivingUnit livingUnit;

    public BookedPeriod() {

    }

    public BookedPeriod(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLivingUnitId() {
        return livingUnitId;
    }

    public void setLivingUnitId(Long livingUnitId) {
        this.livingUnitId = livingUnitId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LivingUnit getLivingUnit() {
        return livingUnit;
    }

    public void setLivingUnit(LivingUnit livingUnit) {
        this.livingUnit = livingUnit;
    }

//    public boolean overlaps(BookedPeriod period) {
//        return !(this.endDate.isBefore(period.startDate) || this.startDate.isAfter(period.endDate));
//    }
}