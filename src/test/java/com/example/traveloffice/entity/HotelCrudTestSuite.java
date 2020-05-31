package com.example.traveloffice.entity;

import com.example.traveloffice.domain.Hotel;
import com.example.traveloffice.repository.HotelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelCrudTestSuite {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void testSaveHotel() {
        //Given
        Hotel hotel = Hotel.builder()
                .name("Lucjusz")
                .city("Radom")
                .stars(3)
                .phoneNumber("000")
                .build();
        //When
        Hotel saveHotel = hotelRepository.save(hotel);
        Long hotelId = saveHotel.getId();
        //Then
        assertNotEquals((Object) 0L, hotelId);
        assertEquals("Lucjusz", saveHotel.getName());
        assertEquals("Radom", saveHotel.getCity());
        assertEquals(3, saveHotel.getStars());
        assertEquals("000", saveHotel.getPhoneNumber());
        //CleanUp
        hotelRepository.deleteById(hotelId);
    }

    @Test
    public void testDeleteHotel() {
        //Given
        Hotel hotel = Hotel.builder()
                .name("Lucjusz")
                .city("Radom")
                .stars(3)
                .phoneNumber("000")
                .build();
        //When
        Hotel saveHotel = hotelRepository.save(hotel);
        Long hotelId = saveHotel.getId();
        hotelRepository.deleteById(hotelId);
        //Then
        assertEquals(0, hotelRepository.findAll().size());
    }


    @Test
    public void testGetAllHotels() {
        //Given
        Hotel hotel1 = Hotel.builder()
                .name("Lucjusz")
                .city("Radom")
                .stars(3)
                .phoneNumber("000")
                .build();

        Hotel hotel2 = Hotel.builder()
                .name("Malfoy")
                .city("Radom")
                .stars(1)
                .phoneNumber("000")
                .build();
        //When
        Hotel saveHotel1 = hotelRepository.save(hotel1);
        Hotel saveHotel2 = hotelRepository.save(hotel2);
        Long hotelId1 = saveHotel1.getId();
        Long hotelId2 = saveHotel2.getId();
        int countOfHotels = hotelRepository.findAll().size();
        //Then
        Assert.assertEquals(2, countOfHotels);
        //CleanUp
        hotelRepository.deleteById(hotelId1);
        hotelRepository.deleteById(hotelId2);
    }


    @Test
    public void testGetHotelById() {
        //Given
        Hotel hotel = Hotel.builder()
                .name("Lucjusz")
                .city("Radom")
                .stars(3)
                .phoneNumber("000")
                .build();
        //When
        Hotel saveHotel = hotelRepository.save(hotel);
        Long hotelId = saveHotel.getId();

        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        //Then
        Assert.assertTrue(optionalHotel.isPresent());
        //CleanUp
        hotelRepository.deleteById(hotelId);
    }
}
