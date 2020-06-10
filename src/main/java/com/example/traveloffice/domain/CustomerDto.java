package com.example.traveloffice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
}
