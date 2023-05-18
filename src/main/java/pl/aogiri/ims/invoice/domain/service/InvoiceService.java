package pl.aogiri.ims.invoice.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aogiri.ims.common.service.BaseCrudService;
import pl.aogiri.ims.invoice.domain.converter.InvoiceConverter;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;
import pl.aogiri.ims.invoice.domain.entity.InvoiceItemEntity;
import pl.aogiri.ims.invoice.domain.repository.InvoiceRepository;
import pl.aogiri.ims.invoice.domain.repository.InvoiceSpecifications;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceBasicResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceDetailsResponse;
import pl.aogiri.ims.invoice.presentation.dto.InvoiceFilterCriteria;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceUpsertRequest;

@Service
@Transactional
public class InvoiceService extends BaseCrudService<InvoiceEntity, InvoiceUpsertRequest, InvoiceDetailsResponse, InvoiceBasicResponse, InvoiceFilterCriteria> {

    public InvoiceService(InvoiceRepository repository, InvoiceConverter converter, InvoiceSpecifications specifications) {
        super(repository, converter, specifications);
    }

}
