package org.kesler.mfc.routeforms.server.controller;

import org.kesler.mfc.routeforms.server.domain.*;
import org.kesler.mfc.routeforms.server.service.RouteFormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by alex on 03.05.16.
 */
@RestController
public class RouteFormsController {

    @Autowired
    protected RouteFormsService routeFormsService;

    @RequestMapping(path = "/branches")
    public Collection<Branch> getBranches() {
        return routeFormsService.findBranches();
    }

    @RequestMapping(path = "/branches", method = RequestMethod.POST)
    public ResponseEntity saveBranch(@RequestBody Branch branch) {
        routeFormsService.saveBranch(branch);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/branches/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeBranch(@PathVariable UUID id) {
        routeFormsService.removeBranch(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @RequestMapping(path = "/employees")
    public Collection<Employee> getEmployees() {
        return routeFormsService.findEmployees();
    }

    @RequestMapping(path = "/employees/active")
    public Collection<Employee> getActiveEmployees() {
        return routeFormsService.findActiveEmployees();
    }

    @RequestMapping(path = "/employees", method = RequestMethod.POST)
    public ResponseEntity saveEmployee(@RequestBody Employee employee) {
        routeFormsService.saveEmployee(employee);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeEmployee(@PathVariable UUID id) {
        routeFormsService.removeEmployee(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @RequestMapping(path = "/drivers")
    public Collection<Driver> getDrivers() {
        return routeFormsService.findDrivers();
    }

    @RequestMapping(path = "/drivers/active/by-branch/{branchId}")
    public Collection<Driver> getActiveDriversForBranch(@PathVariable UUID branchId) {
        Branch branch = routeFormsService.findBranchById(branchId);
        return routeFormsService.findActiveDriversByBranch(branch);
    }

    @RequestMapping(path = "/drivers", method = RequestMethod.POST)
    public ResponseEntity saveDriver(@RequestBody Driver driver) {
        routeFormsService.saveDriver(driver);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/drivers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeDriver(@PathVariable UUID id) {
        routeFormsService.removeDriver(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @RequestMapping(path = "/autos")
    public Collection<Auto> getAutos() {
        return routeFormsService.findAutos();
    }

    @RequestMapping(path = "/autos/by-branch/{branchId}")
    public Collection<Auto> getAutosForBranch(@PathVariable UUID branchId) {
        Branch branch = routeFormsService.findBranchById(branchId);
        return routeFormsService.findAutosByBranch(branch);
    }

    @RequestMapping(path = "/autos", method = RequestMethod.POST)
    public ResponseEntity saveAuto(@RequestBody Auto auto) {
        routeFormsService.saveAuto(auto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/autos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeAuto(@PathVariable UUID id) {
        routeFormsService.removeAuto(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/norms")
    public Collection<Norm> getNorms() {
        return routeFormsService.findNorms();
    }

    @RequestMapping(path = "/norms", method = RequestMethod.POST)
    public ResponseEntity saveNorm(@RequestBody Norm norm) {
        routeFormsService.saveNorm(norm);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/norms/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeNorm(@PathVariable UUID id) {
        routeFormsService.removeNorm(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/routeforms")
    public Collection<RouteForm> getRouteForms() {
        return routeFormsService.findRouteForms();
    }

    @RequestMapping(path = "/routeforms/by-dates/{begDate}to{endDate}")
    public Collection<RouteForm> getRouteFormsDates(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                    @PathVariable LocalDate begDate,
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                    @PathVariable LocalDate endDate) {
        return routeFormsService.findRouteFormsByDates(begDate, endDate);
    }

    @RequestMapping(path = "/routeforms/by-beg-date/{begDate}")
    public Collection<RouteForm> getRouteFormsByBegDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        @PathVariable LocalDate begDate) {
        return routeFormsService.findRouteFormsByBegDate(begDate);
    }

    @RequestMapping(path = "/routeforms/{id}")
    public RouteForm getRouteFormById(@PathVariable UUID id) {
        return routeFormsService.findRouteFormById(id);
    }

    @RequestMapping(path = "/routeforms/previous/{id}")
    public RouteForm getRouteFormByPreviousId(@PathVariable UUID id) {
        return routeFormsService.findRouteFormByPreviousId(id);
    }

    @RequestMapping(path = "/routeforms/by-auto/{autoId}")
    public Collection<RouteForm> getRouteFormsByAuto(@PathVariable UUID autoId) {
        Auto auto =routeFormsService.findAutoById(autoId);
        return routeFormsService.findRouteFormsByAuto(auto);
    }

    @RequestMapping(path = "/routeforms/by-auto/{autoId}/by-dates/{begDate}to{endDate}")
    public Collection<RouteForm> getRouteFormsByAutoAndDates(@PathVariable UUID autoId,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             @PathVariable LocalDate begDate,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             @PathVariable LocalDate endDate) {
        Auto auto =routeFormsService.findAutoById(autoId);
        return routeFormsService.findRouteFormsByAutoAndDates(auto, begDate, endDate);
    }

    @RequestMapping(path = "/routeforms/by-branch/{branchId}")
    public Collection<RouteForm> getRouteFormsByBranch(@PathVariable UUID branchId) {
        Branch branch = routeFormsService.findBranchById(branchId);
        return routeFormsService.findRouteFormsByBranch(branch);
    }

    @RequestMapping(path = "/routeforms/by-branch/{branchId}/by-dates/{begDate}to{endDate}")
    public Collection<RouteForm> getRouteFormsByBranchAndDates(@PathVariable UUID branchId,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             @PathVariable LocalDate begDate,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             @PathVariable LocalDate endDate) {
        Branch branch = routeFormsService.findBranchById(branchId);
        return routeFormsService.findRouteFormsByBranchAndDates(branch, begDate, endDate);
    }

    @RequestMapping(path = "/routeforms/by-branch/{branchId}/by-beg-date/{begDate}")
    public Collection<RouteForm> getRouteFormsByBranchAndBegDate(@PathVariable UUID branchId,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             @PathVariable LocalDate begDate) {
        Branch branch = routeFormsService.findBranchById(branchId);
        return routeFormsService.findRouteFormsByBranchAndBegDate(branch, begDate);
    }



    @RequestMapping(path = "/routeforms", method = RequestMethod.POST)
    public ResponseEntity saveRouteForm(@RequestBody RouteForm routeForm) {
        routeFormsService.saveRouteForm(routeForm);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/routeforms/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeRouteForm(@PathVariable UUID id) {
        routeFormsService.removeRouteForm(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }




    @RequestMapping(path = "/options")
    public ApplicationOptions getOptions() {
        return routeFormsService.loadOptions();
    }

    @RequestMapping(path = "/options", method = RequestMethod.POST)
    public ResponseEntity saveOptions(@RequestBody ApplicationOptions applicationOptions) {
        routeFormsService.saveOptions(applicationOptions);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


}


