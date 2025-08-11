package com.example.dao;

import com.example.model.BookedPeriod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

// TODO: delete?
public interface BookedPeriodsRepository extends PagingAndSortingRepository<BookedPeriod, Long>, CrudRepository<BookedPeriod, Long> {
}
