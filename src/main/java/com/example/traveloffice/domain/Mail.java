package com.example.traveloffice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Mail {

    private String toCc;
    private String mailTo;
    private String Subject;
    private String message;
}

