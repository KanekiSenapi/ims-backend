package pl.aogiri.ims.product.presentation.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import pl.aogiri.ims.common.dto.BaseFilterCriteria;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class ProductFilterCriteria extends BaseFilterCriteria {
    String name;
    UUID customerId;
}
