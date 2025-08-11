package com.example.service;

import com.example.dao.LivingUnitsRepository;
import com.example.model.LivingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class LivingUnitsService {

    // TODO: add database connectivity

    @Autowired
    private LivingUnitsRepository livingUnitsRepository;

    public LivingUnit createLivingUnit(LivingUnit livingUnit) {
        return livingUnitsRepository.save(livingUnit);
    }

    public Page<LivingUnit> findLivingUnits(LivingUnit.UnitType unitType, Integer numberOfRooms, Integer floor,
                                            BigDecimal minCost, BigDecimal maxCost, LocalDate minDate, LocalDate maxDate, Pageable pageable) {

        Specification<LivingUnit> specification = Specification
                .where(LivingUnitSpecifications.hasUnitType(unitType))
                .and(LivingUnitSpecifications.hasNumberOfRooms(numberOfRooms))
                .and(LivingUnitSpecifications.hasFloor(floor))
                .and(LivingUnitSpecifications.costBetween(minCost, maxCost));

        // TODO: add search by date

        return livingUnitsRepository.findAll(specification, pageable);
    }

    public LivingUnit findLivingUnit(long id) {
        return livingUnitsRepository.findById(id).orElse(null);
    }

    public LivingUnit getLivingUnit(long id) {
        return livingUnitsRepository.findById(id).orElse(null);
    }

    public void updateLivingUnit(LivingUnit livingUnit) {
        livingUnitsRepository.save(livingUnit);
    }

    public void deleteLivingUnit(long id) {
        livingUnitsRepository.deleteById(id);
    }
}
