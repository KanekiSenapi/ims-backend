package pl.aogiri.ims.product.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aogiri.ims.common.service.BaseCrudService;
import pl.aogiri.ims.product.domain.converter.ProductConverter;
import pl.aogiri.ims.product.domain.entity.ProductEntity;
import pl.aogiri.ims.product.domain.repository.ProductRepository;
import pl.aogiri.ims.product.domain.repository.ProductSpecifications;
import pl.aogiri.ims.product.presentation.dto.ProductBasicResponse;
import pl.aogiri.ims.product.presentation.dto.ProductDetailsResponse;
import pl.aogiri.ims.product.presentation.dto.ProductFilterCriteria;
import pl.aogiri.ims.product.presentation.dto.ProductUpsertRequest;

import java.util.List;

@Service
@Transactional
public class ProductService extends BaseCrudService<ProductEntity, ProductUpsertRequest, ProductDetailsResponse, ProductBasicResponse, ProductFilterCriteria> {

    public ProductService(ProductRepository repository, ProductConverter converter, ProductSpecifications specifications) {
        super(repository, converter, specifications);
    }

}
