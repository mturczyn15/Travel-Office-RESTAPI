package com.example.traveloffice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "RESPONSES")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESPONSE_ID"
    )
    private Long id;

    @Column()
    private String code;

    @Column()
    private String name;
}
