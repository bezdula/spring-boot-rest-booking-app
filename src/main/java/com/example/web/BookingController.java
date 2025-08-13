package com.example.web;

import com.example.model.BookedPeriod;
import com.example.model.LivingUnit;
import com.example.service.LivingUnitsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;

// TODO: API documentation
@RestController
public class BookingController {

    @Autowired
    private LivingUnitsService livingUnitsService;

    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            // Narrow the primary mapping by specifying file types
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    // Set status code
    @ResponseStatus(HttpStatus.CREATED)
    public void createLivingUnit(@RequestBody LivingUnit livingUnit, HttpServletRequest request, HttpServletResponse response) {
        // Persists unit into database, set some default or missing values and return unit from database
        LivingUnit createdLivingUnit = livingUnitsService.createLivingUnit(livingUnit);
    }

    @RequestMapping(value = "/book",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public void bookLivingUnit(@RequestBody BookedPeriod bookedPeriod, HttpServletRequest request, HttpServletResponse response) {
        if (bookedPeriod.getLivingUnitId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing livingUnitId");
        }

        LivingUnit livingUnit = livingUnitsService.findLivingUnit(bookedPeriod.getLivingUnitId());
        if (livingUnit == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Living unit with specified ID not found");
        }

        livingUnit.addBookedPeriod(bookedPeriod);
        livingUnitsService.updateLivingUnit(livingUnit);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    // pageable example: ?page=0&size=5&sort=name,asc
    public Page<LivingUnit> findLivingUnits(
            @RequestParam(required = false) LivingUnit.UnitType unitType,
            @RequestParam(required = false) Integer numberOfRooms,
            @RequestParam(required = false) Integer floor,
            @RequestParam(required = false) BigDecimal minCost,
            @RequestParam(required = false) BigDecimal maxCost,
            @RequestParam(required = false) LocalDate minDate,
            @RequestParam(required = false) LocalDate maxDate,
            Pageable pageable) {
        return livingUnitsService.findLivingUnits(unitType, numberOfRooms, floor, minCost, maxCost, minDate, maxDate, pageable);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public LivingUnit getLivingUnit(@PathVariable long id) {
        LivingUnit livingUnit = livingUnitsService.getLivingUnit(id);
        checkResourceFound(livingUnit);
        return livingUnit;
    }

//    @RequestMapping(value = "/{id}",
//            method = RequestMethod.PUT,
//            consumes = {"application/json", "application/xml"},
//            produces = {"application/json", "application/xml"})
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateLivingUnit(@PathVariable long id, @RequestBody LivingUnit livingUnit) {
//        checkResourceFound(livingUnitsService.getLivingUnit(id));
//        if (id != livingUnit.getId()) {
//            throw new RuntimeException("ID doesn't match!");
//        }
//
//        livingUnitsService.updateLivingUnit(livingUnit);
//    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLivingUnit(@PathVariable long id) {
        checkResourceFound(livingUnitsService.getLivingUnit(id));

        livingUnitsService.deleteLivingUnit(id);
    }

    public static <T> void checkResourceFound(T resource) {
        if (resource == null) {
            throw new RuntimeException("resource not found");
        }
    }
}
