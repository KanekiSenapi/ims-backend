package pl.aogiri.ims.invoice.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.aogiri.ims.common.controller.BaseCrudController;

@Tag(name = "Invoices", description = "Operations related to invoices")
@RequestMapping("/invoice")
public interface InvoiceController<UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria>
        extends BaseCrudController<UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria> {
}
