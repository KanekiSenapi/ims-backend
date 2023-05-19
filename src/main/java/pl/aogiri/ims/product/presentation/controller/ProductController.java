package pl.aogiri.ims.product.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.aogiri.ims.common.controller.BaseCrudController;

@Tag(name = "Products", description = "Operations related to products")
@RequestMapping("/product")
public interface ProductController<UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria>
        extends BaseCrudController<UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria> {
}
