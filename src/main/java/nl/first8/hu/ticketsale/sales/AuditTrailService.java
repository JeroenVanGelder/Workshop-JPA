package nl.first8.hu.ticketsale.sales;

import nl.first8.hu.ticketsale.registration.Account;
import nl.first8.hu.ticketsale.registration.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by jeroenvangelder on 7-6-17.
 */

@Service
public class AuditTrailService {
    private final AuditTrailRepository auditTrailRepository;
    private final RegistrationRepository registrationRepository;
    private final SalesRepository salesRepository;

    @Autowired
    public AuditTrailService(AuditTrailRepository auditTrailRepository,RegistrationRepository registrationRepository, SalesRepository salesRepository)
    {
        this.auditTrailRepository = auditTrailRepository;
        this.registrationRepository = registrationRepository;
        this.salesRepository = salesRepository;

    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void insertAuditTrail(Long accountId, Long saleId)
    {
        Account account = registrationRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Unknown account Id " + accountId));
        Optional<Sale> auditedSale = salesRepository.findSaleById(saleId);
        AuditTrail auditTrail = new AuditTrail();
        auditTrail.setAccount(account);
        auditTrail.setSale(auditedSale.get());
        auditTrailRepository.insert(auditTrail);
    }
}
