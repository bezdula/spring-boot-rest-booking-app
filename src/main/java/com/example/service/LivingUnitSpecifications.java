package com.example.service;

import com.example.model.LivingUnit;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivingUnitSpecifications {

    public static Specification<LivingUnit> hasUnitType(LivingUnit.UnitType unitType) {
        return (root, query, criteriaBuilder) -> unitType == null ? null : criteriaBuilder.equal(root.get("unitType"), unitType);
    }

    public static Specification<LivingUnit> hasNumberOfRooms(Integer rooms) {
        return (root, query, criteriaBuilder) -> rooms == null ? null : criteriaBuilder.equal(root.get("numberOfRooms"), rooms);
    }

    public static Specification<LivingUnit> hasFloor(Integer floor) {
        return (root, query, criteriaBuilder) -> floor == null ? null : criteriaBuilder.equal(root.get("floor"), floor);
    }

    public static Specification<LivingUnit> costBetween(BigDecimal minCost, BigDecimal maxCost) {
        return (root, query, criteriaBuilder) -> {
            if (minCost != null && maxCost != null) {
                return criteriaBuilder.between(root.get("cost"), minCost, maxCost);
            } else if (minCost != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost);
            } else if (maxCost != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
            } else {
                return null;
            }
        };
    }

    public static Specification<LivingUnit> dateBetween(LocalDate minDate, LocalDate maxDate) {
        return (root, query, criteriaBuilder) -> {
            if (minDate != null && maxDate != null) {
                return criteriaBuilder.between(root.get("date"), minDate, maxDate);
            } else if (minDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("date"), minDate);
            } else if (maxDate != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("date"), maxDate);
            } else {
                return null;
            }
        };
    }
}
