package org.kesler.mfc.routeforms.server.repository;

import org.kesler.mfc.routeforms.server.domain.Auto;
import org.kesler.mfc.routeforms.server.domain.Branch;
import org.kesler.mfc.routeforms.server.domain.RouteForm;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для Путевых листов
 */
public interface RouteFormRepository {
    List<RouteForm> findRouteForms() throws DataAccessException;
    List<RouteForm> findRouteFormsByDates(LocalDate begDate, LocalDate endDate) throws DataAccessException;
    List<RouteForm> findRouteFormsByBegDate(LocalDate begDate) throws DataAccessException;
    RouteForm findRouteFormById(UUID id) throws DataAccessException;
    RouteForm findRouteFormByPreviousId(UUID id) throws DataAccessException;
    List<RouteForm> findRouteFormsByAuto(Auto auto) throws DataAccessException;
    List<RouteForm> findRouteFormsByAutoAndDates(Auto auto, LocalDate begDate, LocalDate endDate) throws DataAccessException;
    List<RouteForm> findRouteFormsByBranch(Branch branch) throws DataAccessException;
    List<RouteForm> findRouteFormsByBranchAndDates(Branch branch, LocalDate begDate, LocalDate endDate) throws DataAccessException;
    List<RouteForm> findRouteFormsByBranchAndBegDate(Branch branch, LocalDate begDate) throws DataAccessException;
    void save(RouteForm routeForm) throws DataAccessException;
    void remove(UUID id) throws DataAccessException;
}
