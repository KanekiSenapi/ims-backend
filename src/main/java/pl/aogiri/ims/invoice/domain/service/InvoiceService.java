package pl.aogiri.ims.invoice.domain.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aogiri.ims.common.service.BaseCrudService;
import pl.aogiri.ims.invoice.domain.converter.InvoiceConverter;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;
import pl.aogiri.ims.invoice.domain.repository.InvoiceRepository;
import pl.aogiri.ims.invoice.domain.repository.InvoiceSpecifications;
import pl.aogiri.ims.invoice.presentation.dto.InvoiceFilterCriteria;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceBasicResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceDetailsResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceUpsertRequest;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class InvoiceService extends BaseCrudService<InvoiceEntity, InvoiceUpsertRequest, InvoiceDetailsResponse, InvoiceBasicResponse, InvoiceFilterCriteria> {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository repository, InvoiceConverter converter, InvoiceSpecifications specifications,
                          InvoiceRepository invoiceRepository) {
        super(repository, converter, specifications);
        this.invoiceRepository = invoiceRepository;
    }

    public List<InvoiceEntity> findRaw(InvoiceFilterCriteria filterCriteria) {
        Specification<InvoiceEntity> specification = specifications.filterByCriteria(filterCriteria);
        return repository.findAll(specification);
    }

    public void update(UUID invoiceId, String uri) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(invoiceId).orElseThrow();
        invoiceEntity.setFile(URI.create(uri));
        invoiceRepository.save(invoiceEntity);
    }
}
