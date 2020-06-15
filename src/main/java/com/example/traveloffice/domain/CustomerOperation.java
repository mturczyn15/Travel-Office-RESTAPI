package com.example.traveloffice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "CUSTOMER_OPERATIONS")
public class CustomerOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUSTOMER")
    private String customer;

    @Column(name = "OPERATION")
    @Enumerated(EnumType.STRING)
    private Operation operation;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "TIME")
    private LocalTime time;
}
