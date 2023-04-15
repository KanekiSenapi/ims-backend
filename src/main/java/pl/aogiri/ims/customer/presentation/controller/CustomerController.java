package pl.aogiri.ims.customer.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.aogiri.ims.common.controller.BaseCrudController;
import pl.aogiri.ims.customer.presentation.dto.CustomerDTO;

@Tag(name = "Customers", description = "Operations related to customers")
@RequestMapping("/customer")
public interface CustomerController extends BaseCrudController<CustomerDTO> {
}
