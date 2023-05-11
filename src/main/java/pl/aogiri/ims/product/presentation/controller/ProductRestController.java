package pl.aogiri.ims.product.presentation.controller;

import org.springframework.web.bind.annotation.RestController;
import pl.aogiri.ims.common.controller.BaseCrudRestController;
import pl.aogiri.ims.product.domain.entity.ProductEntity;
import pl.aogiri.ims.product.domain.service.ProductService;
import pl.aogiri.ims.product.presentation.dto.ProductBasicResponse;
import pl.aogiri.ims.product.presentation.dto.ProductDetailsResponse;
import pl.aogiri.ims.product.presentation.dto.ProductFilterCriteria;
import pl.aogiri.ims.product.presentation.dto.ProductUpsertRequest;

import java.util.List;

@RestController
public class ProductRestController
        extends BaseCrudRestController<ProductEntity, ProductUpsertRequest, ProductDetailsResponse, ProductBasicResponse, ProductFilterCriteria>
        implements ProductController<ProductUpsertRequest, ProductDetailsResponse, ProductBasicResponse, ProductFilterCriteria> {

    public ProductRestController(ProductService productService) {
        super(productService);
    }
}
