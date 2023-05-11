package pl.aogiri.ims.customer.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aogiri.ims.common.service.BaseCrudService;
import pl.aogiri.ims.customer.domain.converter.CustomerConverter;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.customer.domain.repository.CustomerRepository;
import pl.aogiri.ims.customer.domain.repository.CustomerSpecifications;
import pl.aogiri.ims.customer.presentation.dto.CustomerBasicResponse;
import pl.aogiri.ims.customer.presentation.dto.CustomerDetailsResponse;
import pl.aogiri.ims.customer.presentation.dto.CustomerFilterCriteria;
import pl.aogiri.ims.customer.presentation.dto.CustomerUpsertRequest;

@Service
@Transactional
public class CustomerService extends BaseCrudService<CustomerEntity, CustomerUpsertRequest, CustomerDetailsResponse, CustomerBasicResponse, CustomerFilterCriteria> {

    public CustomerService(CustomerRepository repository, CustomerConverter converter, CustomerSpecifications specifications) {
        super(repository, converter, specifications);
    }

}
