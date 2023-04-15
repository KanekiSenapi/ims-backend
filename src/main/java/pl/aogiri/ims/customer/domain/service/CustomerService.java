package pl.aogiri.ims.customer.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aogiri.ims.common.service.BaseCrudService;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.customer.presentation.dto.CustomerDTO;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomerService extends BaseCrudService<CustomerEntity, CustomerDTO> {
}
