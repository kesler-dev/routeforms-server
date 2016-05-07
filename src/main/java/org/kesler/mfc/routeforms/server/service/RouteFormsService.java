package org.kesler.mfc.routeforms.server.service;

import org.kesler.mfc.routeforms.server.domain.*;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

/**
 * Единая точка входа для всех запросов к даннным
 */
public interface RouteFormsService {

    Collection<Branch> findBranches() throws DataAccessException;
    Branch findBranchById(UUID id) throws DataAccessException;
    void saveBranch(Branch branch) throws DataAccessException;
    void removeBranch(UUID id) throws DataAccessException;

    Collection<Employee> findEmployees() throws DataAccessException;
    Collection<Employee> findActiveEmployees() throws DataAccessException;
    void saveEmployee(Employee employee) throws DataAccessException;
    void removeEmployee(UUID id) throws DataAccessException;

    Collection<Driver> findDrivers() throws DataAccessException;
    Collection<Driver> findActiveDriversByBranch(Branch branch) throws DataAccessException;
    void saveDriver(Driver driver) throws DataAccessException;
    void removeDriver(UUID id) throws DataAccessException;

    Collection<Auto> findAutos() throws DataAccessException;
    Collection<Auto> findAutosByBranch(Branch branch) throws DataAccessException;
    Auto findAutoById(UUID id) throws DataAccessException;
    void saveAuto(Auto auto) throws DataAccessException;
    void removeAuto(UUID id) throws DataAccessException;

    Collection<Norm> findNorms() throws DataAccessException;
    void saveNorm(Norm norm) throws DataAccessException;
    void removeNorm(UUID id) throws DataAccessException;


    Collection<RouteForm> findRouteForms() throws DataAccessException;
    Collection<RouteForm> findRouteFormsByDates(LocalDate begDate, LocalDate endDate) throws DataAccessException;
    RouteForm findRouteFormById(UUID id) throws DataAccessException;
    Collection<RouteForm> findRouteFormsByAuto(Auto auto) throws DataAccessException;
    Collection<RouteForm> findRouteFormsByAutoAndDates(Auto auto, LocalDate begDate, LocalDate endDate) throws DataAccessException;
    Collection<RouteForm> findRouteFormsByBranch(Branch branch) throws DataAccessException;
    Collection<RouteForm> findRouteFormsByBranchAndDates(Branch branch, LocalDate begDate, LocalDate endDate) throws DataAccessException;
    void saveRouteForm(RouteForm routeForm) throws DataAccessException;
    void removeRouteForm(UUID id) throws DataAccessException;

    ApplicationOptions loadOptions() throws DataAccessException;
    void saveOptions(ApplicationOptions applicationOptions) throws DataAccessException;

}
