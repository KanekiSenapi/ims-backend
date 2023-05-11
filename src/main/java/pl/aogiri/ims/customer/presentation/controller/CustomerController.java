package pl.aogiri.ims.customer.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.aogiri.ims.common.controller.BaseCrudController;

@Tag(name = "Customers", description = "Operations related to customers")
@RequestMapping("/customer")
public interface CustomerController<UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria>
        extends BaseCrudController<UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria> {
}
