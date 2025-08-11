package com.example.dao;

import com.example.model.LivingUnit;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LivingUnitsRepository extends PagingAndSortingRepository<LivingUnit, Long>, CrudRepository<LivingUnit, Long>, JpaSpecificationExecutor<LivingUnit> {
}
