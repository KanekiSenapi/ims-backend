package pl.aogiri.ims.invoice.presentation.controller;

import org.springframework.web.bind.annotation.RestController;
import pl.aogiri.ims.common.controller.BaseCrudRestController;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;
import pl.aogiri.ims.invoice.domain.service.InvoiceService;
import pl.aogiri.ims.invoice.presentation.dto.InvoiceFilterCriteria;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceBasicResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceDetailsResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceUpsertRequest;

@RestController
public class InvoiceRestController
        extends BaseCrudRestController<InvoiceEntity, InvoiceUpsertRequest, InvoiceDetailsResponse, InvoiceBasicResponse, InvoiceFilterCriteria>
        implements InvoiceController<InvoiceUpsertRequest, InvoiceDetailsResponse, InvoiceBasicResponse, InvoiceFilterCriteria> {

    public InvoiceRestController(InvoiceService invoiceService) {
        super(invoiceService);
    }

}
