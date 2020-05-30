package com.example.traveloffice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
    private Long id;
    private Long customerId;
    private String street;
    private String houseNumber;
    private String city;
    private String zipCode;
    private String phoneNumber;
}
