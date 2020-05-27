package com.example.traveloffice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class TravelAgencyDto {
    private Long id;
    private String name;
    private String city;
    private String phoneNumber;
}
