package org.kesler.mfc.routeforms.server.service;


import org.kesler.mfc.routeforms.server.domain.*;
import org.kesler.mfc.routeforms.server.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

/**
 * Единая точка входа для всех запросов к даннным,
 * место для размещения @Transactional
 */
@Service
@Transactional
public class RouteFormsServiceImpl implements RouteFormsService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired protected BranchRepository branchRepository;
    @Autowired protected EmployeeRepository employeeRepository;
    @Autowired protected DriverRepository driverRepository;
    @Autowired protected AutoRepository autoRepository;
    @Autowired protected NormRepository normRepository;
    @Autowired protected RouteFormRepository routeFormRepository;
    @Autowired protected ApplicationOptionsRepository applicationOptionsRepository;


    /**
     *   Филиалы
     */

    @Override
    @Transactional(readOnly = true)
    public Collection<Branch> findBranches() throws DataAccessException {
        log.info("Getting all Branches..");
        Collection<Branch> branches = branchRepository.findBranches();
        log.info("Recieved " + branches.size() + " Branches");
        return branches;
    }

    @Override
    @Transactional(readOnly = true)
    public Branch findBranchById(UUID id) throws DataAccessException {
        log.info("Getting Branch by id: " + id);
        Branch branch = branchRepository.findBranchById(id);
        log.info("Recieved " + branch );
        return branch;
    }

    @Override
    public void saveBranch(Branch branch) throws DataAccessException {
        log.info("Saving Branch: "+ branch.getName());
        branchRepository.save(branch);
        log.info("Saving complete");
    }

    @Override
    public void removeBranch(UUID id) throws DataAccessException {
        log.info("Removing Branch: " + id);
        branchRepository.remove(id);
        log.info("Removing complete");
    }



    /**
     *   Сотрудники
     */

    @Override
    @Transactional(readOnly = true)
    public Collection<Employee> findEmployees() throws DataAccessException {
        log.info("Getting all Employees..");
        Collection<Employee> employees = employeeRepository.findEmployees();
        log.info("Recieved " + employees.size() + " Employees");
        return employees;
    }

    @Override
    public Collection<Employee> findActiveEmployees() throws DataAccessException {
        log.info("Getting active Employees..");
        Collection<Employee> employees = employeeRepository.findEmployees();
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            Employee em = it.next();
            if (!em.isActive()) it.remove();
        }
        boolean hasAdmin = false;
        for (Employee employee : employees) {
            if (employee.isAdmin()) {
                hasAdmin = true;
                break;
            }
        }

        if (!hasAdmin) {
            log.info("Admin not exist - creating");
            Employee admin = new Employee();
            admin.setFio("Админ");
            admin.setAdmin(true);
            admin.setPassword("admin");
            admin.setActive(true);
            employeeRepository.save(admin);
            employees.add(admin);
        }


        log.info("Recieved " + employees.size() + " Employees");
        return employees;
    }

    @Override
    public void saveEmployee(Employee employee) throws DataAccessException {
        log.info("Saving Employee: "+ employee.getFio());
        employeeRepository.save(employee);
        log.info("Saving complete");
    }

    @Override
    public void removeEmployee(UUID id) throws DataAccessException {
        log.info("Removing Employee: " + id);
        employeeRepository.remove(id);
        log.info("Removing complete");
    }

    /**
     *   Сотрудники
     */

    @Override
    @Transactional(readOnly = true)
    public Collection<Driver> findDrivers() throws DataAccessException {
        log.info("Getting all Drivers..");
        Collection<Driver> drivers = driverRepository.findDrivers();
        log.info("Received " + drivers.size() + " Drivers");
        return drivers;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Driver> findActiveDriversByBranch(Branch branch) throws DataAccessException {
        log.info("Getting Drivers by Branch " + branch);
        Collection<Driver> drivers = driverRepository.findActiveDriversByBranch(branch);
        log.info("Received " + drivers.size() + " Drivers");
        return drivers;
    }

    @Override
    public void saveDriver(Driver driver) throws DataAccessException {
        log.info("Saving Driver: "+ driver.getFioShort());
        driverRepository.save(driver);
        log.info("Saving complete");
    }

    @Override
    public void removeDriver(UUID id) throws DataAccessException {
        log.info("Removing Driver: " + id);
        driverRepository.remove(id);
        log.info("Removing complete");
    }

    /**
     *   Автомобили
     */

    @Override
    @Transactional(readOnly = true)
    public Collection<Auto> findAutos() throws DataAccessException {
        log.info("Getting all Autos..");
        Collection<Auto> autos = autoRepository.findAutos();
        log.info("Recieved " + autos.size() + " Autos");
        return autos;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Auto> findAutosByBranch(Branch branch) throws DataAccessException {
        log.info("Getting Autos by Branch: " + branch);
        Collection<Auto> autos = autoRepository.findAutosByBranch(branch);
        log.info("Recieved " + autos.size() + " Autos");
        return autos;
    }

    @Override
    @Transactional(readOnly = true)
    public Auto findAutoById(UUID id) throws DataAccessException {
        log.info("Getting Auto id: " + id);
        Auto auto = autoRepository.findAutoById(id);
        log.info("Recieved " + auto);
        return auto;
    }

    @Override
    public void saveAuto(Auto auto) throws DataAccessException {
        log.info("Saving Auto: "+ auto.toString());
        autoRepository.save(auto);
        log.info("Saving complete");
    }

    @Override
    public void removeAuto(UUID id) throws DataAccessException {
        log.info("Removing Auto: " + id);
        autoRepository.remove(id);
        log.info("Removing complete");
    }

    /**
     *   Нормы
     */

    @Override
    @Transactional(readOnly = true)
    public Collection<Norm> findNorms() throws DataAccessException {
        log.info("Getting all Norms..");
        Collection<Norm> norms = normRepository.findNorms();
        log.info("Recieved " + norms.size() + " Norms");
        return norms;
    }

    @Override
    public void saveNorm(Norm norm) throws DataAccessException {
        log.info("Saving Norm: "+ norm.toString());
        normRepository.save(norm);
        log.info("Saving complete");
    }

    @Override
    public void removeNorm(UUID id) throws DataAccessException {
        log.info("Removing Norm: " + id);
        normRepository.remove(id);
        log.info("Removing complete");
    }


  
    @Override
    public Collection<RouteForm> findRouteForms() throws DataAccessException {
        log.info("Getting all RouteForms..");
        Collection<RouteForm> routeForms = routeFormRepository.findRouteForms();
        log.info("Received " + routeForms.size() + " RouteForms");
        return routeForms;
    }


    @Override
    public Collection<RouteForm> findRouteFormsByDates(LocalDate begDate, LocalDate endDate) throws DataAccessException {
        log.info("Getting RouteForms by dates: " + begDate + " - " + endDate);
        Collection<RouteForm> routeForms = routeFormRepository.findRouteFormsByDates(begDate, endDate);
        log.info("Received " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
    public RouteForm findRouteFormById(UUID id) throws DataAccessException {
        log.info("Getting RouteForm by id: " + id);
        if (id==null) return null;
        RouteForm routeForm = routeFormRepository.findRouteFormById(id);
        log.info("Received RouteForm " + routeForm);
        return routeForm;
    }

    @Override
    public Collection<RouteForm> findRouteFormsByAuto(Auto auto) throws DataAccessException {
        log.info("Getting RouteForms by auto " + auto);
        Collection<RouteForm> routeForms = routeFormRepository.findRouteFormsByAuto(auto);
        log.info("Received " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
    public Collection<RouteForm> findRouteFormsByAutoAndDates(Auto auto, LocalDate begDate, LocalDate endDate) throws DataAccessException {
        log.info("Getting RouteForms by auto " + auto + " and dates: " + begDate + " - " + endDate);
        Collection<RouteForm> routeForms = routeFormRepository.findRouteFormsByAutoAndDates(auto, begDate, endDate);
        log.info("Received " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
    public Collection<RouteForm> findRouteFormsByBranch(Branch branch) throws DataAccessException {
        log.info("Getting RouteForms by branch " + branch);
        Collection<RouteForm> routeForms = routeFormRepository.findRouteFormsByBranch(branch);
        log.info("Recieved " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
    public Collection<RouteForm> findRouteFormsByBranchAndDates(Branch branch, LocalDate begDate, LocalDate endDate) throws DataAccessException {
        log.info("Getting RouteForms by branch " + branch + " and dates: " + begDate + " - " + endDate);
        Collection<RouteForm> routeForms = routeFormRepository.findRouteFormsByBranchAndDates(branch, begDate, endDate);
        log.info("Received " + routeForms.size() + " RouteForms");
        return routeForms;
    }


    @Override
    public void saveRouteForm(RouteForm routeForm) throws DataAccessException {
        log.info("Saving RouteForm: "+ routeForm.toString());
        routeFormRepository.save(routeForm);
        log.info("Saving complete");

    }

    @Override
    public void removeRouteForm(UUID id) throws DataAccessException {
        log.info("Removing RouteForm: " + id);
        routeFormRepository.remove(id);
        log.info("Removing complete");
    }




    @Override
    public ApplicationOptions loadOptions() throws DataAccessException {
        log.info("Loading Options");
        ApplicationOptions applicationOptions = applicationOptionsRepository.load();
        if (applicationOptions == null) {
            log.info("Options not found. Creating new Options.");
            applicationOptions = new ApplicationOptions();
        }
        return applicationOptions;
    }

    @Override
    public void saveOptions(ApplicationOptions applicationOptions) throws DataAccessException {
        applicationOptionsRepository.save(applicationOptions);
    }

}
