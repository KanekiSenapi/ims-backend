package pl.aogiri.ims.customer.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailsResponse {
    private UUID id;
    private String name;
    private String nip;
    private String address;
}
