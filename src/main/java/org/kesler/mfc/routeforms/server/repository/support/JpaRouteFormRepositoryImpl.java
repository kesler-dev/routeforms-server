package org.kesler.mfc.routeforms.server.repository.support;

import org.kesler.mfc.routeforms.server.domain.Auto;
import org.kesler.mfc.routeforms.server.domain.Branch;
import org.kesler.mfc.routeforms.server.domain.RouteForm;
import org.kesler.mfc.routeforms.server.repository.RouteFormRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * JPA репозиторий для водителей
 */
@Repository
public class JpaRouteFormRepositoryImpl implements RouteFormRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RouteForm> findRouteForms() throws DataAccessException {
        log.info("Reading all RouteForms..");
        Query query = em.createQuery("SELECT DISTINCT routeForm FROM RouteForm routeForm");
        List<RouteForm> routeForms = query.getResultList();
        log.info("Read " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
    public List<RouteForm> findRouteFormsByDates(LocalDate begDate, LocalDate endDate) throws DataAccessException {
        log.info("Reading RouteForms by dates: " + begDate + " - " + endDate);
        TypedQuery<RouteForm> query = em.createQuery("SELECT DISTINCT routeForm FROM RouteForm routeForm WHERE " +
                "routeForm.date>=:begDate AND routeForm.date<=:endDate", RouteForm.class);
        query.setParameter("begDate", begDate);
        query.setParameter("endDate", endDate);
        List<RouteForm> routeForms = query.getResultList();
        log.info("Read " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
     public RouteForm findRouteFormById(UUID id) throws DataAccessException {
        log.info("Reading RouteForms by id: " + id);
        TypedQuery<RouteForm> query = em.createQuery("SELECT routeForm FROM RouteForm routeForm WHERE routeForm.id=:id", RouteForm.class);
        query.setParameter("id", id);
        RouteForm routeForm = query.getSingleResult();
        log.info("Read " + routeForm);
        return routeForm;
    }

    @Override
    public List<RouteForm> findRouteFormsByAuto(Auto auto) throws DataAccessException {
        log.info("Reading RouteForms by Auto: " + auto);
        TypedQuery<RouteForm> query = em.createQuery("SELECT DISTINCT routeForm FROM RouteForm routeForm WHERE routeForm.auto=:auto", RouteForm.class);
        query.setParameter("auto", auto);
        List<RouteForm> routeForms = query.getResultList();
        log.info("Read " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
    public List<RouteForm> findRouteFormsByAutoAndDates(Auto auto, LocalDate begDate, LocalDate endDate) throws DataAccessException {
        log.info("Reading RouteForms by Auto: " + auto + " and dates: " + begDate + " - " + endDate);
        TypedQuery<RouteForm> query = em.createQuery("SELECT DISTINCT routeForm FROM RouteForm routeForm WHERE routeForm.auto=:auto " +
                "AND routeForm.date>=:begDate AND routeForm.date<=:endDate", RouteForm.class);
        query.setParameter("auto", auto);
        query.setParameter("begDate", begDate);
        query.setParameter("endDate", endDate);
        List<RouteForm> routeForms = query.getResultList();
        log.info("Read " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
    public List<RouteForm> findRouteFormsByBranch(Branch branch) throws DataAccessException {
        log.info("Reading RouteForms by Branch: " + branch);
        TypedQuery<RouteForm> query = em.createQuery("SELECT DISTINCT routeForm FROM RouteForm routeForm WHERE routeForm.auto.branch=:branch", RouteForm.class);
        query.setParameter("branch", branch);
        List<RouteForm> routeForms = query.getResultList();
        log.info("Read " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
    public List<RouteForm> findRouteFormsByBranchAndDates(Branch branch, LocalDate begDate, LocalDate endDate) throws DataAccessException {
        log.info("Reading RouteForms by Branch: " + branch + " and dates " + begDate + " - " + endDate);
        TypedQuery<RouteForm> query = em.createQuery("SELECT DISTINCT routeForm FROM RouteForm routeForm WHERE routeForm.auto.branch=:branch " +
                "AND routeForm.date>=:begDate AND routeForm.date<=:endDate", RouteForm.class);
        query.setParameter("branch", branch);
        query.setParameter("begDate", begDate);
        query.setParameter("endDate", endDate);
        List<RouteForm> routeForms = query.getResultList();
        log.info("Read " + routeForms.size() + " RouteForms");
        return routeForms;
    }

    @Override
    public void save(RouteForm routeForm) throws DataAccessException {
        log.info("Saving RouteForm: " + routeForm.toString());
        if (routeForm.getId() == null) {
            log.debug("Persisting..");
            em.persist(routeForm);
        } else {
            log.debug("Merging..");
            em.merge(routeForm);
        }
        log.info("Saving complete");
    }

    @Override
    public void remove(UUID id) throws DataAccessException {
        log.info("Removing RouteForm: " + id);
        RouteForm managedRouteForm = em.getReference(RouteForm.class, id);
        em.remove(managedRouteForm);
        log.info("Removing complete");
    }
}
