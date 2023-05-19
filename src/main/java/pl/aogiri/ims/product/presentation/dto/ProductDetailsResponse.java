package pl.aogiri.ims.product.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aogiri.ims.customer.presentation.dto.CustomerBasicResponse;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsResponse {
    private UUID id;
    private String name;
    private BigDecimal netUnitPrice;
    private CustomerBasicResponse customer;
}
