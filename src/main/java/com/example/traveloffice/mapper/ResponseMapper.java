package com.example.traveloffice.mapper;

import com.example.traveloffice.domain.Response;
import com.example.traveloffice.domain.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {
    public Response map(final ResponseDto responseDto) {
        return Response.builder()
                .id(null)
                .name(responseDto.getName())
                .code(responseDto.getCode())
                .build();
    }
}
