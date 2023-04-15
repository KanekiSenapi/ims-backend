package pl.aogiri.ims.customer.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpsertRequest {
    private String name;
    private String krs;
    private String nip;
    private String regon;
    private String address;
}