package com.example.traveloffice.domain;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {

    private Long id;
    private Long mainAddressId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
}
