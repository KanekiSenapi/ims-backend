package pl.aogiri.ims.customer.presentation.controller;

import org.springframework.web.bind.annotation.RestController;
import pl.aogiri.ims.common.controller.BaseCrudRestController;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.customer.domain.service.CustomerService;
import pl.aogiri.ims.customer.presentation.dto.CustomerBasicResponse;
import pl.aogiri.ims.customer.presentation.dto.CustomerDetailsResponse;
import pl.aogiri.ims.customer.presentation.dto.CustomerFilterCriteria;
import pl.aogiri.ims.customer.presentation.dto.CustomerUpsertRequest;

@RestController
public class CustomerRestController
        extends BaseCrudRestController<CustomerEntity, CustomerUpsertRequest, CustomerDetailsResponse, CustomerBasicResponse, CustomerFilterCriteria>
        implements CustomerController<CustomerUpsertRequest, CustomerDetailsResponse, CustomerBasicResponse, CustomerFilterCriteria> {

    public CustomerRestController(CustomerService customerService) {
        super(customerService);
    }

}
