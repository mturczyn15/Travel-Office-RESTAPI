package com.example.traveloffice.validator;

import com.example.traveloffice.domain.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HotelValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelValidator.class);

    public void validateHotel(final Hotel hotel) {
        if (hotel.getName().contains("test")) {
            LOGGER.info("Someone is testing my application!");
        } else {
            LOGGER.info("Seems like my application is used in proper way.");
        }
    }
}
