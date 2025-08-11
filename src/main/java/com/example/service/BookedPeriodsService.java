package com.example.service;

import com.example.dao.BookedPeriodsRepository;
import com.example.model.BookedPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: delete
@Service
public class BookedPeriodsService {

    @Autowired
    private BookedPeriodsRepository bookedPeriodsRepository;

    public BookedPeriod createBookedPeriod(BookedPeriod bookedPeriod) {
        return bookedPeriodsRepository.save(bookedPeriod);
    }
}
