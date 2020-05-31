package com.example.traveloffice.entity;

import com.example.traveloffice.domain.TravelAgency;
import com.example.traveloffice.repository.TravelAgencyRepository;
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
public class TravelAgencyTestSuite {
    @Autowired
    private TravelAgencyRepository travelAgencyRepository;

    @Test
    public void testSaveTravelAgency() {
        //Given
        TravelAgency travelAgency = TravelAgency.builder()
                .city("Radom")
                .name("Agency")
                .phoneNumber("000")
                .build();
        //When
        TravelAgency saveTravelAgency = travelAgencyRepository.save(travelAgency);
        Long travelAgencyId = saveTravelAgency.getId();
        //Then
        assertNotEquals((Object) 0L, travelAgencyId);
        //CleanUp
        travelAgencyRepository.deleteById(travelAgencyId);
    }

    @Test
    public void testDeleteTravelAgency() {
        //Given
        TravelAgency travelAgency = TravelAgency.builder()
                .city("Radom")
                .name("Agency")
                .phoneNumber("000")
                .build();
        //When
        TravelAgency saveTravelAgency = travelAgencyRepository.save(travelAgency);
        Long travelAgencyId = saveTravelAgency.getId();
        travelAgencyRepository.deleteById(travelAgencyId);
        //Then
        assertEquals(0, travelAgencyRepository.findAll().size());
    }


    @Test
    public void testGetAllTravelAgencyes() {
        //Given
        TravelAgency travelAgency1 = TravelAgency.builder()
                .city("Radom")
                .name("Agency")
                .phoneNumber("000")
                .build();

        TravelAgency travelAgency2 = TravelAgency.builder()
                .city("Radom")
                .name("Agency")
                .phoneNumber("000")
                .build();
        //When
        TravelAgency saveTravelAgency1 = travelAgencyRepository.save(travelAgency1);
        TravelAgency saveTravelAgency2 = travelAgencyRepository.save(travelAgency2);
        Long travelAgencyId1 = saveTravelAgency1.getId();
        Long travelAgencyId2 = saveTravelAgency2.getId();
        int countOfTravelAgencies = travelAgencyRepository.findAll().size();
        //Then
        Assert.assertEquals(2, countOfTravelAgencies);
        //CleanUp
        travelAgencyRepository.deleteById(travelAgencyId1);
        travelAgencyRepository.deleteById(travelAgencyId2);
    }


    @Test
    public void testGetTravelAgencyById() {
        TravelAgency travelAgency = TravelAgency.builder()
                .city("Radom")
                .name("Agency")
                .phoneNumber("000")
                .build();

        TravelAgency saveTravelAgency = travelAgencyRepository.save(travelAgency);
        Long travelAgencyId = saveTravelAgency.getId();

        Optional<TravelAgency> optionalTravelAgency = travelAgencyRepository.findById(travelAgencyId);
        Assert.assertTrue(optionalTravelAgency.isPresent());
        Assert.assertEquals("Radom", saveTravelAgency.getCity());
        Assert.assertEquals("Agency", saveTravelAgency.getName());
        Assert.assertEquals("000", saveTravelAgency.getPhoneNumber());
        travelAgencyRepository.deleteById(travelAgencyId);
    }
}
