package com.java.TravelAgency.controller;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.OfferDto;
import com.java.TravelAgency.entity.Offer;
import com.java.TravelAgency.service.OfferService;
import com.java.TravelAgency.utils.OffersMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferControllerTest {

    @InjectMocks
    OfferController offerController;
    @Mock
    OfferService offerService;
    OfferDto offerDto;
    Offer offer;


    @Test
    public void getAllOffersTest() throws ParseException {
        //GIVEN
        offerDto = OffersMocks.mockOfferDto();

        List<OfferDto> offerDtos = new ArrayList<>();
        offerDtos.add(offerDto);

        //WHEN
        when(offerService.getAllOffers()).thenReturn(offerDtos);

        //THEN
        ResponseEntity<List<OfferDto>> result = offerController.getAllOffers();
        assertEquals(result.getBody(), offerDtos);
        assertEquals(result.getStatusCode().value(), 200);
    }



    @Test
    public void addNewOfferTest() throws ParseException {
        //GIVEN
        offer = OffersMocks.mockOffer();

        //WHEN
        when(offerService.addOffer(offer)).thenReturn(offerDto);

        //THEN
        ResponseEntity<OfferDto> result = offerController.addNewOffer(offer);
        assertEquals(result.getBody(), offerDto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void deleteAdoptedOffersTest() throws ParseException {
        //GIVEN
        offer = OffersMocks.mockOffer();
        //WHEN
        when( offerService.deleteOffer(offer.getId())).thenReturn(Boolean.valueOf(Constants.OBJECT_DELETED));

        //THEN
        ResponseEntity<String> result = offerController.deleteOffer(offer.getId());
        assertEquals(result.getBody(), Constants.OBJECT_DELETED);
        assertEquals(result.getStatusCode().value(), 200);
    }



}

