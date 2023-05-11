package pl.aogiri.ims.customer.presentation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;
import pl.aogiri.ims.common.dto.BaseFilterCriteria;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerFilterCriteria extends BaseFilterCriteria {
    private String name;
}
