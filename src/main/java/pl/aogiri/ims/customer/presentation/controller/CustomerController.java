package pl.aogiri.ims.customer.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import pl.aogiri.ims.common.controller.BaseCrudController;
import pl.aogiri.ims.customer.presentation.dto.CustomerDTO;

@RequestMapping("/customer")
public interface CustomerController extends BaseCrudController<CustomerDTO> {
}
