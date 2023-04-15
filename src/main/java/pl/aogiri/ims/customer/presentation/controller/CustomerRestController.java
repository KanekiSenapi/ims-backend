package pl.aogiri.ims.customer.presentation.controller;

import org.springframework.web.bind.annotation.RestController;
import pl.aogiri.ims.common.controller.BaseCrudRestController;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.customer.domain.service.CustomerService;
import pl.aogiri.ims.customer.presentation.dto.CustomerDTO;

@RestController
public class CustomerRestController
        extends BaseCrudRestController<CustomerEntity, CustomerDTO>
        implements CustomerController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        super(customerService);
        this.customerService = customerService;
    }
}
