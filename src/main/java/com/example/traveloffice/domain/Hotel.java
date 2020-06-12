package com.example.traveloffice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "HOTELS")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HOTEL_ID")
    private Long id;

    @Column(name = "HOTEL_NAME")
    private String name;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STARS")
    @Enumerated(EnumType.STRING)
    private Stars stars;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}
