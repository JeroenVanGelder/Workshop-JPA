package nl.first8.hu.ticketsale.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * Created by jeroenvangelder on 7-6-17.
 */

@RestController
@RequestMapping("/audittrail")
@Transactional
public class AuditTrailResource {

    private final AuditTrailService service;

    @Autowired
    public AuditTrailResource(AuditTrailService service) { this.service = service; }



}
