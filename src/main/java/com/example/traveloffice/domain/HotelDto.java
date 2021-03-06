package com.example.traveloffice.domain;

import lombok.Data;

@Data
public class HotelDto {

    private Long id;
    private final String name;
    private final String city;
    private final Stars stars;
    private final String phoneNumber;

    public static class HotelDtoBuilder {
        private Long id;
        private String name;
        private String city;
        private Stars stars;
        private String phoneNumber;

        public HotelDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public HotelDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public HotelDtoBuilder city(String city) {
            this.city = city;
            return this;
        }

        public HotelDtoBuilder stars(Stars stars) {
            this.stars = stars;
            return this;
        }

        public HotelDtoBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public HotelDto build() {
            return new HotelDto(id, name, city, stars, phoneNumber );
        }
    }

    public HotelDto(Long id, String name, String city, Stars stars, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stars = stars;
        this.phoneNumber = phoneNumber;
    }
}
