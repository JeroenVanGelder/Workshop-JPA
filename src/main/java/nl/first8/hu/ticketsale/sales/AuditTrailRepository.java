package nl.first8.hu.ticketsale.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by jeroenvangelder on 7-6-17.
 */
@Repository
public class AuditTrailRepository {

    private final EntityManager entityManager;

    @Autowired
    public AuditTrailRepository(EntityManager entityManager) { this.entityManager = entityManager; }

    public void insert(final AuditTrail auditTrail) { entityManager.persist(auditTrail);}

}
